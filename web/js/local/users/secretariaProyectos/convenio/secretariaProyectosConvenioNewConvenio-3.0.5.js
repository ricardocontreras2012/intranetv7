
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

    const dataString = $("#convenio-form").serialize();
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

    const dataString = $("#convenio-form").serialize();
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
    const rut = rutdv.substring(0, rutdv.length - 2);
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
        const rut = $("#rut").val();
        const dia = $("#dia").find("option:selected").val();
        const inicio = $("#fechaInicio").val();
        const termino = $("#fechaTermino").val();
        const hrInicio = $("#timeInicio").val();
        const hrTermino = $("#timeTermino").val();
        $("#add-horario-modal").modal('hide');

        const dataString = {
            'key': $("#key").val(),
            'rut': rut,
            'dia': dia,
            'fechaInicio': inicio,
            'fechaTermino': termino,
            'horaInicio': hrInicio,
            'horaTermino': hrTermino
        };
        jQuery.ajax({
            url: "SecretariaProyectosConvenioTopeHorario",
            type: "POST",
            data: dataString,
            success: function (data) {
                $("#dummy").html(data);
            },
            async: false
        });

        const str = $("#result-status-div").html().trim();
        if (str === "Error")
        {
            $("#error-div").html("<p>" + $("#result-error-div").html().replaceAll("#", "</p>"));
            $("#curso-search-modal").modal('hide');
            $("#error-modal").modal('show');
        } else
        {
            addHorario();

            const rowCount = $('#horario-table tr').length;
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
    let ret = $("#newRow").val();
    ret++;
    $("#newRow").val(ret);
    return ret;
}

function addHorario()
{
    const dia = $("#dia").find("option:selected").val();
    const inicio = $("#timeInicio").val();
    const termino = $("#timeTermino").val();
    const fila = getNewRowId();
    const col1 = '<td><input type="text" id="dia_' + fila + '" name="dia_' + fila + '" class="form-control" value="' + dia + '"/></td>';
    const col2 = '<td><input type="text" id="inicio_' + fila + '" name="inicio_' + fila + '" class="form-control" value="' + inicio + '"/></td>';
    const col3 = '<td><input type="text" id="termino_' + fila + '" name="termino_' + fila + '" class="form-control" value="' + termino + '"/></td>';
    const row = "<tr>" + col1 + col2 + col3 + "</tr>";

    $("#timeInicio").val("");
    $("#timeTermino").val("");
    $("#horario-table").append(row);
}

function getCursos()
{
    const agno = $("#agno").val();
    const sem = $("#sem").val();
    const proyecto = $("#proyecto option:selected").val();
    const rut = $("#rut").val();
    const tipo = $('#tipoContrato').find("option:selected").val();

    let action;

    if (tipo === "DOC")
    {
        action = "SecretariaProyectosConvenioGetCursos";
    } else
    {
        action = "SecretariaProyectosConvenioGetCursosAyudante";
    }

    const dataString = {'key': $("#key").val(), 'rut': rut, 'agno': agno, 'sem': sem, 'proyecto': proyecto};
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
    const proyecto = $("#proyecto option:selected").val();

    const dataString = {'key': $("#key").val(), 'proyecto': proyecto};
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
    const rut = $("#rutdvFirma").val();
    $("#rutFirma").val(rut.substring(0, rut.length - 2));
    $("#nombre-firmma-div").html($("#dummy-nombre-jefe-div").html());

    $('#tipoContrato').val('')[0].scrollIntoView();
    $("#funcion").val("");
    checkDates();
}

function changeFirma()
{
    const rut = $("#rutFirma").val();
    const dataString = {'key': $("#key").val(), 'rut': rut};
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
    //if ($("#fechaInicio").val() !== "" && $("#fechaTermino").val() !== "" && $("#proyecto").find("option:selected").val() !== "0") {
        $('#tipoContrato').attr("style", "pointer-events: auto;");
    //}
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
        const regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        const aux = element.name === "rutdv" ? "rut" : "rutFirma";
        return this.optional(element) || (regExp.test(jQuery.trim(value)) && validateRutDv(element.name, aux));
    }, "Rut-Dv inválido");

    $("#rutdv").keyup(function () {
        const rut = formatear($(this).val(), true);
        $(this).val(rut);
    });

    $("#rutdv").blur(function () {
        const validator = $("#convenio-form").validate();
        validator.element("#rutdv");
    });

    $("#rutdvFirma").keyup(function () {
        const rut = formatear($(this).val(), true);
        $(this).val(rut);
    });

    $("#rutdvFirma").blur(function () {
        const validator = $("#convenio-form").validate();
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
        const inicio = $(this).val();
        if (inicio !== "")
        {
            const pattern = /^([01]?[0-9]|2[0-3]):[0-5][0-9]/;
            if (!pattern.test(inicio)) {
                alert('valor' + inicio + ' es inválido');
                $("#timeInicio").val("");
                return false;
            }
        }
    });

    $("#timeTermino").blur(function () {
        const termino = $(this).val();
        if (termino !== "")
        {
            const pattern = /^([01]?[0-9]|2[0-3]):[0-5][0-9]/;
            if (!pattern.test(termino)) {
                alert('valor' + termino + ' es inválido');
                $("#timeTermino").val("");
                $("#timeTermino").get(0).focus();
            }
        }
    });
    
    const picker = flatpickr("#fecha", {
        dateFormat: "d-m-Y",
        locale: "es",
        allowInput: true,
        clickOpens: false
    });

    document.getElementById("btnCalendario").addEventListener("click", (e) => {
        e.preventDefault();  // evita comportamiento por defecto del botón
        picker.open();
    });

     const pickerTermino = flatpickr("#fechaTermino", {
        dateFormat: "d-m-Y",
        locale: "es",
        allowInput: true,
        clickOpens: false
    });

    document.getElementById("btnCalendarioTermino").addEventListener("click", (e) => {
        e.preventDefault();  // evita comportamiento por defecto del botón
        pickerTermino.open();
    });

    const pickerInicio = flatpickr("#fechaInicio", {
        dateFormat: "d-m-Y",
        locale: "es",
        allowInput: true,
        clickOpens: false,
        onChange: function (selectedDates) {
            pickerTermino.set('minDate', selectedDates[0]);
        }
    });

    document.getElementById("btnCalendarioInicio").addEventListener("click", (e) => {
        e.preventDefault();
        pickerInicio.open();
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
        const tipo = $(this).val();
        const $funcion = $("#funcion");
        const $horarioDiv = $("#horario-div");

        $funcion.val(""); // Reinicia campo "funcion"

        if (tipo === "DOC" || tipo === "AYU") {
            $funcion.prop("disabled", true);
            $horarioDiv.val("").css("visibility", "hidden");
            $("#curso-search-modal").modal("show");
            getCursos();
        } else if (["SER", "SEC", "DPG"].includes(tipo)) {
            $("#horario-table-div").html(`
            <table id="horario-table" class="table">
                <tr><th>Día</th><th>Hora Inicio</th><th>Hora Término</th></tr>
            </table>
        `);
            $("#horario-modal").modal("show");
        } else if (tipo === "EXT") {
            $funcion.prop("disabled", false);
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
