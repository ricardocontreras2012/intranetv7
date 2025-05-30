
function save() {
    if (validateField("fecha", "") +
            validateField("rutdv", "") +
            validateField("rutdvFirma", "") +
            validateField("paterno", "") +
            validateField("direccion", "") +
            validateField("fecha", "") +
            validateField("fechaInicio", "") +
            validateField("fechaTermino", "") +
            validateField("funcion", "") +
            validateField("monto", "") +
            validateSelect("proyecto", "") +
            validateSelect("tipoContrato", "") === 0)
    {
        setAux();
        $("#convenio-form").attr("action", "SecretariaProyectosConvenioSaveConvenio").attr("target", "_self").submit();
        return true;
    }
    return false;
}

function setAux()
{
    $("#rutAux").val($("#rut").val());
    $("#rutFirmaAux").val($("#rutFirma").val());
    $("#direccionAux").val($("#direccion").val());
    $("#proyectoAux").val($("#proyecto").find("option:selected").val());
    $("#fechaAux").val($("#fecha").val());
    $("#fechaInicioAux").val($("#fechaInicio").val());
    $("#fechaTerminoAux").val($("#fechaTermino").val());
    $("#tipoContratoAux").val($("#tipoContrato").find("option:selected").val());
    $("#funcionAux").val($("#funcion").val());
    $("#montoAux").val($("#monto").val());
    $("#tipoMontoAux").val($("#tipoMonto").val());
    $("#obsPagoAux").val($("#obsPago").val());

    $("#rutdv").val("");
    $("#rutdvFirma").val("");
    $("#nombre-firmma-div").html("");
    $("#rut").val("");
    $("#rutFirma").val("");
    $("#direccion").val("");
    $("#proyecto").val("0");
    $("#fecha").val("");
    $("#fechaInicio").val("");
    $("#fechaTermino").val("");
    $("#tipoContrato").val("0");
    $("#funcion").val("");
    $("#monto").val("");
    $("#tipoMonto").val("G");
    $("#obsPago").val("");
    $("#paterno").val("");
    $("#materno").val("");
    $("#nombres").val("");
}

function validateField(field, value)
{
    if ($("#" + field).val() === value)
    {
        blink($("#" + field), 'red', 500, 3);
        return 1;
    }
    return 0;
}

function validateSelect(field, value)
{
    if ($("#" + field).find("option:selected").val() === value)
    {
        blink($("#" + field), 'red', 500, 3);
        return 1;
    }
    return 0;
}

function searchRut()
{
    $("#paterno").val("");
    $("#materno").html("");
    $("#nombres").html("");
    $("#direccion").val("");

    var dataString = $("#convenio-form").serialize();
    jQuery.ajax({
        url: "SecretariaProyectosConvenioFuncionarioSearch",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#funcionario-search-modal-div").html(data);
        },
        async: false
    });

    $("#funcionario-search-modal").modal('show');
}

function searchPaterno()
{
    $("#rutdv").val("");
    $("#rut").val("");
    $("#materno").html("");
    $("#nombres").html("");
    $("#direccion").val("");

    var dataString = $("#convenio-form").serialize();
    jQuery.ajax({
        url: "SecretariaProyectosConvenioFuncionarioSearch",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#funcionario-search-modal-div").html(data);
        },
        async: false
    });

    $("#funcionario-search-modal").modal('show');
}

function addFuncionarioList(rutdv, paterno, materno, nombres, direccion)
{
    var rut = rutdv.substring(0, rutdv.length - 2);
    $("#rutdv").val(rutdv);
    $("#rut").val(rut);
    $("#paterno").val(paterno);
    $("#materno").html(materno);
    $("#nombres").html(nombres);
    $("#direccion").val(direccion);
    $("#funcionario-search-modal").modal('hide');
    enableProyecto();
}

function newHorario()
{
    $("#horario-modal-div").html($("#diaDummy-div").html());
    $("#add-horario-modal").modal('show');
}

function validateHorario()
{
    if ($("#timeInicio").val() !== "" && $("#timeTermino").val() !== "")
    {
        var rut = $("#rut").val();
        var dia = $("#dia").find("option:selected").val();
        var inicio = $("#fechaInicio").val();
        var termino = $("#fechaTermino").val();
        var hrInicio = $("#timeInicio").val();
        var hrTermino = $("#timeTermino").val();
        $("#add-horario-modal").modal('hide');

        var dataString = {'key': $("#key").val(), 'rut': rut, 'dia': dia, 'fechaInicio': inicio, 'fechaTermino': termino, 'horaInicio': hrInicio, 'horaTermino': hrTermino};
        jQuery.ajax({
            url: "SecretariaProyectosConvenioTopeHorario",
            type: "POST",
            data: dataString,
            success: function (data) {
                $("#dummy").html(data);
            },
            async: false
        });

        var str = $("#result-status-div").html().trim();
        if (str === "Error")
        {
            $("#error-div").html("<p>" + $("#result-error-div").html().replaceAll("#", "</p>"));
            $("#curso-search-modal").modal('hide');
            $("#error-modal").modal('show');
        } else
        {
            addHorario();

            var rowCount = $('#horario-table tr').length;
            if (rowCount === 2)
            {
                switch ($("#tipoContrato").find("option:selected").val()) {
                    case 'DOC':
                        break;
                    case 'AYU':
                        break;
                    case 'SER':
                        $("#funcion").prop("disabled", false);
                        break;
                    case 'SEC':
                        $("#funcion").val("Labores de secretaria y coordinación");
                        break;
                    case 'DPG':
                        $("#funcion").val("Director");
                        break;
                    case 'EXT':
                        $("#funcion").prop("disabled", false);
                        break;
                    default:
                }
            }
        }
    }
}

function getNewRowId()
{
    var ret = $("#newRow").val();
    ret++;
    $("#newRow").val(ret);
    return ret;
}

function addHorario()
{
    var dia = $("#dia").find("option:selected").val();
    var inicio = $("#timeInicio").val();
    var termino = $("#timeTermino").val();
    var fila = getNewRowId();
    var col1 = '<td><input type="text" id="dia_' + fila + '" name="dia_' + fila + '" class="form-control" value="' + dia + '"/></td>';
    var col2 = '<td><input type="text" id="inicio_' + fila + '" name="inicio_' + fila + '" class="form-control" value="' + inicio + '"/></td>';
    var col3 = '<td><input type="text" id="termino_' + fila + '" name="termino_' + fila + '" class="form-control" value="' + termino + '"/></td>';
    var row = "<tr>" + col1 + col2 + col3 + "</tr>";

    $("#timeInicio").val("");
    $("#timeTermino").val("");
    $("#horario-table").append(row);
}

function getCursos()
{
    var agno = $("#agno").val();
    var sem = $("#sem").val();
    var proyecto = $("#proyecto option:selected").val();
    var rut = $("#rut").val();
    var tipo = $('#tipoContrato').find("option:selected").val();

    var action;

    if (tipo === "DOC")
    {
        action = "SecretariaProyectosConvenioGetCursos";
    } else
    {
        action = "SecretariaProyectosConvenioGetCursosAyudante";
    }

    var dataString = {'key': $("#key").val(), 'rut': rut, 'agno': agno, 'sem': sem, 'proyecto': proyecto};
    jQuery.ajax({
        url: action,
        type: "GET",
        data: dataString,
        success: function (data) {
            $("#curso-list-div").html(data);
        },
        async: false
    });
}

function clearBasic()
{
    var proyecto = $("#proyecto option:selected").val();

    var dataString = {'key': $("#key").val(), 'proyecto': proyecto};
    jQuery.ajax({
        url: "SecretariaProyectosConvenioGetProyecto",
        type: "GET",
        data: dataString,
        success: function (data) {
            $("#jefe-div").html(data);
        },
        async: false
    });

    $("#rutdvFirma").val($("#dummy-rut-jefe-div").html());
    var rut = $("#rutdvFirma").val();
    $("#rutFirma").val(rut.substring(0, rut.length - 2));
    $("#nombre-firmma-div").html($("#dummy-nombre-jefe-div").html());

    $('#tipoContrato').val('')[0].scrollIntoView();
    $("#funcion").val("");
    checkDates();
}

function changeFirma()
{
    var rut = $("#rutFirma").val();
    var dataString = {'key': $("#key").val(), 'rut': rut};
    jQuery.ajax({
        url: "SecretariaProyectosConvenioGetProfesor",
        type: "GET",
        data: dataString,
        success: function (data) {
            $("#jefe-div").html(data);
        },
        async: false
    });
    $("#nombre-firmma-div").html($("#dummy-nombre-jefe-div").html());
}

function checkDates()
{
    if ($("#fechaInicio").val() !== "" && $("#fechaTermino").val() !== "" && $("#proyecto").find("option:selected").val() !== "0") {
        $('#tipoContrato').attr("style", "pointer-events: auto;");
    }
}

function enableProyecto()
{
    if ($("#rutdv").val() !== "")
    {
        $('#proyecto').attr("style", "pointer-events: auto;");
    }
}

function acceptHorario()
{
    $("#horario-modal").modal('hide');
}

$(document).ready(function () {
    $("#search-rut-button").click(searchRut);
    $("#search-firma-button").click(changeFirma);
    $("#search-paterno-button").click(searchPaterno);
    $("#search-curso-button").click(getCursos);
    $("#save-button").click(save);
    $("#add-horario-button").click(newHorario);
    $("#accept-horario-button").click(acceptHorario);

    jQuery.validator.addMethod("rutdv", function (value, element) {
        var regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        var aux = element.name === "rutdv" ? "rut" : "rutFirma";
        return this.optional(element) || (regExp.test(jQuery.trim(value)) && validateRutDv(element.name, aux));
    }, "Rut-Dv inválido");

    $("#rutdv").keyup(function () {
        var rut = formatear($(this).val(), true);
        $(this).val(rut);
    });

    $("#rutdv").blur(function () {
        var validator = $("#convenio-form").validate();
        validator.element("#rutdv");
    });

    $("#rutdvFirma").keyup(function () {
        var rut = formatear($(this).val(), true);
        $(this).val(rut);
    });

    $("#rutdvFirma").blur(function () {
        var validator = $("#convenio-form").validate();
        validator.element("#rutdvFirma");
    });

    $('#new-horario-event').on('click', function (evt) {
        validateHorario();
    });
    $('#del-horario-event').on('click', function (evt) {
        delHorario();
    });

    $("#timeInicio").mask("99:99");
    $("#timeTermino").mask("99:99");

    $("#timeInicio").blur(function () {
        var inicio = $(this).val();
        if (inicio !== "")
        {
            var pattern = /^([01]?[0-9]|2[0-3]):[0-5][0-9]/;
            if (!pattern.test(inicio)) {
                alert('valor' + inicio + ' es inválido');
                $("#timeInicio").val("");
                return false;
            }
        }
    });

    $("#timeTermino").blur(function () {
        var termino = $(this).val();
        if (termino !== "")
        {
            var pattern = /^([01]?[0-9]|2[0-3]):[0-5][0-9]/;
            if (!pattern.test(termino)) {
                alert('valor' + termino + ' es inválido');
                $("#timeTermino").val("");
                $("#timeTermino").get(0).focus();
            }
        }
    });

    $("#fecha").datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd-mm-yyyy',
        weekStartDay: 1
    });

    $("#fechaInicio").datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd-mm-yyyy',
        weekStartDay: 1
    });

    $("#fechaTermino").datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd-mm-yyyy',
        weekStartDay: 1,
        minDate: function () {
            return $('#fechaInicio').val();
        }
    });

    $("#monto").keyup(function (e)
    {
        if (/\D/g.test(this.value))
        {
            this.value = this.value.replace(/\D/g, '');
        }
    });

    $("#horario-doc-div").css("visibility", "hidden");
    $("#horario-div").css("visibility", "hidden");

    $('#proyecto').attr("style", "pointer-events: none;");
    $('#tipoContrato').attr("style", "pointer-events: none;");

    $("#funcion").prop("disabled", true);

    $('#tipoContrato').on('change', function () {
        var optionSelected = $(this).find("option:selected").val();
        $("#funcion").val("");

        switch (optionSelected) {
            case 'DOC':
                $("#funcion").prop("disabled", true);
                $("#horario-div").val("");
                $("#horario-div").css("visibility", "hidden");
                $("#curso-search-modal").modal('show');
                getCursos();
                break;
            case 'AYU':
                $("#funcion").prop("disabled", true);
                $("#horario-div").val("");
                $("#horario-div").css("visibility", "hidden");
                $("#curso-search-modal").modal('show');
                getCursos();
                break;
            case 'SER':
            case 'SEC':
            case 'DPG':
                $("#horario-table-div").html('<table id="horario-table" class="table"><tr><th>Día</th><th>Hora Inicio</th><th>Hora Término</th></tr></table>');
                $("#horario-modal").modal('show');
                break;
            case 'EXT':
                $("#funcion").prop("disabled", false);
            default:
        }
    });

    $("#convenio-form").validate({
        rules: {
            rutdv: {rutdv: true},
            rutdvFirma: {rutdv: true}
        },
        messages: {
            rutdv: {required: jQuery.validator.messages.required},
            rutdvFirma: {required: jQuery.validator.messages.required}
        }
    });

});
