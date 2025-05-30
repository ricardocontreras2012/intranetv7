function getNewRowId()
{
    var ret = $("#newRow").val();
    ret++;
    $("#newRow").val(ret);
    return ret;
}

function showBlocked()
{
    $("#error-div").html("<p> Debe tener definido el horario completo y de acuerdo al TEL </p>");
    $("#error-modal").modal('show');
}

function existeCurso(asign, elect, coord, secc)
{
    var table = $('#cursos-table').DataTable();
    var rowCount = $('#cursos-table tr').length;
    var curso;
    var existe = false;
    for (var i = 0; i < rowCount - 1; i++)
    {
        if (typeof table.cell(i, 1).data() !== 'undefined')
        {
            curso = table.cell(i, 1).data().replace("&nbsp;", " ");

            if (curso === asign + "-" + elect + " " + coord + secc)
            {
                existe = true;
                break;
            }
        }
    }

    return existe;
}

function validateCurso()
{
    resetCss();
    var msg = "";
    var flag = true;
    $("#coord").val(($("#coord").val()).toUpperCase());
    var optionSelected = $("#asign").find("option:selected");

    var asign = optionSelected.val();
    var elect = $("#elect").val();
    var coord = $("#coord").val();
    var secc = $("#secc").val();
    var cupo = $("#cupo").val();
    var inicio = $("#inicio").val();
    var termino = $("#termino").val();

    if (asign === "0")
    {
        $('#asign').addClass("error");
        msg = "Falta definir asignatura";
        flag = false;
    }
    if (elect === "")
    {
        $('#elect').addClass("error");
        msg = "Falta definir electividad";
        flag = false;
    }
    if (coord === "")
    {
        $('#coord').addClass("error");
        msg = "Falta definir coordinación";
        flag = false;
    }
    if (secc === "")
    {
        $('#secc').addClass("error");
        msg = "Falta definir sección";
        flag = false;
    }

    if (asign !== "" && elect !== "" && coord !== "" && secc !== "")
    {

        if ($("#userType").val() !== "CFI" && !($("#diurno").is(":checked") || $("#vespertino").is(":checked")))
        {
            flag = false;
            msg = "Falta definir jornada";
        }

        if (existeCurso(asign, elect, coord, secc))
        {
            flag = false;
            msg = "Curso ya está definido";
        }

        //$("#curso-msg").html(msg);
    }

    if (cupo === "")
    {
        $('#cupo').val(0);
    }

    var partesInicio = inicio.split("-");
    partesInicio[0] = parseInt(partesInicio[0], 10).toString();
    if (partesInicio[0].length === 2) {
        partesInicio[0] = "20" + partesInicio[0];
    }
    $("#inicio").val(partesInicio.join("-"));

    var partesTermino = termino.split("-");
    partesTermino[0] = parseInt(partesTermino[0], 10).toString();
    if (partesTermino[0].length === 2) {
        partesTermino[0] = "20" + partesTermino[0];
    }
    $("#termino").val(partesTermino.join("-"));

    if ($("#inicio").val() > $("#termino").val())
    {
        flag = false;
        msg = "Fecha de inicio debe ser menor o igual la de término";
    }

    if (!flag && msg !=="")
    {       
        $("#error-div").html(msg);
        $("#error-modal").modal('show').css('z-index', 1051);
    }
    return flag;
}

function validateCupoId() {
    var cupoId = $("#cupoId").val();
    if (cupoId === "")
    {
        $('#cupoId').val(0);
    }
}

function addCurso() {
    if (validateCurso())
    {
        $("#cursos-form").attr("action", "CommonCursoDefinicionAddCurso?key=" + $("#key").val()).attr("target", "_self").submit();
    }
    return false;
}

function modifyCurso() {
    validateCupoId();
    if ($("#inicioId").val() <= $("#terminoId").val())
    {
        $("#cursos-form").attr("action", "CommonCursoDefinicionModifyCurso?key=" + $("#key").val()).attr("target", "_self").submit();
    } else
    {
        $("#error-div").html("Fecha de inicio debe ser menor o igual a la de término");
        $("#error-modal").modal('show').css('z-index', 1051);
    }
}

function resetCss()
{
    $('#asign').removeClass("error");
    $('#elect').removeClass("error");
    $('#coord').removeClass("error");
    $('#secc').removeClass("error");
}

function newCurso()
{
    $('#asign option').eq(0).prop('selected', true);
    $("#elect").val('');
    $("#electividad").val('');
    $("#coord").val('');
    $("#secc").val('');
    $("#cupo").val('0');
    $('#elect').attr('readonly', true);
    $('#electivo').attr('readonly', true);
    resetCss();
    $("#curso-msg").html("");
    $("#new-modal").modal('show');
}

function scrollToPos() {
    var pos = $("#pos").val();

    if (pos !== null && pos > 0)
    {
        var rows = document.querySelectorAll('#cursos-table tr');
        pos++;
        rows[pos].scrollIntoView({
            behavior: 'auto',
            block: 'start'
        });
    }
}

function deleteCursos() {
    if (anyChecked("cursos-form") === true) {
        $("#confirmacion").modal('show');
    } else {
        $("#msg").modal('show');
    }
    return false;
}

function remove()
{
    $("#cursos-form").attr("action", "CommonCursoDefinicionRemoveCursos?key=" + $("#key").val()).attr("target", "_self").submit();
    $("confirmacion").modal('hide');
}

function printLista() {
    $("#cursos-form").attr("action", 'CommonCursoDefinicionPrint').attr("target", "_blank").submit();
}

function exportLista() {
    $("#cursos-form").attr("action", 'CommonCursoDefinicionExport').attr("target", "_blank").submit();
}

function showCurso()
{
    var pos = $("#pos").val();
    var table = $('#cursos-table').DataTable();
    var row = table.cell(pos, 1).data().replace("&nbsp;", " ");

    var posAsign = row.indexOf("-");
    var posElect = row.indexOf(" ");
    var asign = row.substring(0, posAsign);
    var elect = row.substring(posAsign + 1, posElect);
    var coord = row.substring(posElect + 1, posElect + 2);
    var secc = row.substring(posElect + 2);
    var electivo;
    var cupo = table.cell(pos, 7).data();
    var diurno = table.cell(pos, 9).data();
    var vesp = table.cell(pos, 10).data();
    var inicio = table.cell(pos, 11).data();
    var termino = table.cell(pos, 12).data();

    var dataString = {"asign": asign, "elect": elect, "key": $("#key").val()};
    jQuery.ajax({
        url: "CommonCursoDefinicionGetElectivo",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#electivo-div").html(data);
        },
        async: false
    });

    electivo = $("#electivo-div").html();

    $("#asignId").html(asign + " " + table.cell(pos, 2).data());
    $("#electId").html(elect);
    //$('#electivoId').attr('readonly', false);
    if (elect === '0' || $("#tipo").val() === 'E')
    {
        $('#electivoId').attr('readonly', true);
    }
    $("#coordId").html(coord);
    $("#seccId").html(secc);
    $("#electivoId").val(electivo);
    $("#cupoId").val(cupo);

    var partes = inicio.split("-");
    if (partes[2] && partes[2].length === 2) {
        partes[2] = "20" + partes[2];
        $("#inicioId").val(partes[2] + "-" + partes[1] + "-" + partes[0]);
    } else
    {
        $("#inicioId").val(inicio);
    }

    var partes = termino.split("-");
    if (partes[2] && partes[2].length === 2) {
        partes[2] = "20" + partes[2];
        $("#terminoId").val(partes[2] + "-" + partes[1] + "-" + partes[0]);
    } else
    {
        $("#terminoId").val(termino);
    }

    $("#diurnoId").prop('checked', false);
    if (diurno === "D")
    {
        $("#diurnoId").prop('checked', true);
    }

    $("#vespertinoId").prop('checked', false);
    if (vesp === "V")
    {
        $("#vespertinoId").prop('checked', true);
    }

    $("#modify-modal").modal('show');
}

function showProfesor(curso)
{
    $("#profesor-table").remove();
    $("#profesor-estricto-table").remove();
    var pos = $("#pos").val();
    var titulo = '<h5 class="modal-title">PROFESOR: ' + curso + '</h5>';
    $("#prof-title").html(titulo);
    $("#modal-profesor").modal('show');

    var dataString = {'key': $("#key").val(), 'pos': pos, 'actionCall': 'CommonCursoDefinicionToolGetProfesor'};
    jQuery.ajax({
        url: "CommonCursoGetCursoActual",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#profesor-modal-div").html(data);
        },
        async: false
    });

    return false;
}

function showProfesorEstricto(curso)
{
    $("#profesor-table").remove();
    $("#profesor-estricto-table").remove();
    var pos = $("#pos").val();
    var titulo = '<h5 class="modal-title">PROFESOR: ' + curso + '</h5>';
    $("#prof-estricto-title").html(titulo);
    $("#modal-profesor-estricto").modal('show');

    var dataString = {'key': $("#key").val(), 'pos': pos, 'actionCall': 'CommonCursoDefinicionToolGetProfesorEstricto'};
    jQuery.ajax({
        url: "CommonCursoGetCursoActual",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#profesor-estricto-modal-div").html(data);
        },
        async: false
    });

    return false;
}

function showAyudante(curso)
{
    $("#ayudante-table").remove();
    $("#ayudante-estricto-table").remove();
    var pos = $("#pos").val();
    var titulo = '<h5 class="modal-title">AYUDANTE: ' + curso + '</h5>';
    $("#ayu-title").html(titulo);
    $("#ayudante-modal").modal('show');

    var dataString = {'key': $("#key").val(), 'pos': pos, 'actionCall': 'CommonCursoDefinicionToolGetAyudante'};
    jQuery.ajax({
        url: "CommonCursoGetCursoActual",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#ayudante-modal-div").html(data);
        },
        async: false
    });

    return false;
}

function showAyudanteEstricto(curso)
{
    $("#ayudante-table").remove();
    $("#ayudante-estricto-table").remove();
    var pos = $("#pos").val();
    var titulo = '<h5 class="modal-title">AYUDANTE: ' + curso + '</h5>';
    $("#ayu-estricto-title").html(titulo);
    $("#ayudante-estricto-modal").modal('show');

    var dataString = {'key': $("#key").val(), 'pos': pos, 'actionCall': 'CommonCursoDefinicionToolGetAyudanteEstricto'};
    jQuery.ajax({
        url: "CommonCursoGetCursoActual",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#ayudante-estricto-modal-div").html(data);
        },
        async: false
    });

    return false;
}

function showHorario(curso)
{
    var pos = $("#pos").val();
    var titulo = '<h5 class="modal-title">HORARIO: ' + curso + '</h5>';
    $("#hor-title").html(titulo);
    $("#horario-modal").modal('show');

    var dataString = {'key': $("#key").val(), 'pos': pos, 'actionCall': 'CommonCursoDefinicionToolGetHorario'};
    jQuery.ajax({
        url: "CommonCursoGetCursoActual",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#horario-modal-div").html(data);
        },
        async: false
    });

    return false;
}

function addHorario()
{
    var optionsDia = "";
    $("#diaDummy option").each(function () {
        optionsDia = optionsDia + "<option value='" + $(this).val() + "'>" + $(this).val() + "</option>";
    });

    var optionsMod = "";
    $("#modDummy option").each(function () {
        optionsMod = optionsMod + "<option value='" + $(this).val() + "'>" + $(this).val() + "</option>";
    });

    var optionsSala = "";
    $("#salaDummy option").each(function () {
        optionsSala = optionsSala + "<option value='" + $(this).val() + "'>" + $(this).val() + "</option>";
    });

    var fila = getNewRowId();
    var col1 = '<td><input type="checkbox" id="hor_' + fila + '" name="hor_' + fila + '" class="form-control" /></td>';
    var col2 = '<td><select id="dia_' + fila + '" name="dia_' + fila + '" class="form-control">' + optionsDia + '</select></td>';
    var col3 = '<td><select id="mod_' + fila + '" name="mod_' + fila + '" class="form-control">' + optionsMod + '</select></td>';
    var col4 = '<td><select id=sala_' + fila + '" name="sala_' + fila + '" class="form-control">' + optionsSala + '</select></td>';
    var col5 = '<td><select id="tipo_' + fila + '" name="tipo_' + fila + '" class="form-control"><option value="C">T</option><option value="A">E</option></td>';
    var row = "<tr>" + col1 + col2 + col3 + col4 + col5 + "</tr>";

    $("#horario-table").append(row);
}

function delHorario()
{
    $("#del-horario-modal").modal('hide');

    var tableControl = document.getElementById('horario-table');
    $('input:checkbox:checked', tableControl).each(function () {
        $(this).closest('tr').remove();
    });
}

function modalDelHorario()
{
    $("#del-horario-modal").modal('show');
}

function modalSaveHorario()
{
    $("#save-horario-modal").modal('show');
}

function saveHorario()
{
    $("#save-horario-modal").modal('hide');
    $("#horario-modal").modal('hide');
    $("#horarioDummy").html("");

    var telTeo = Math.floor(parseInt($("#asiHcredTeo").val()) / 2);
    var telEje = Math.floor(parseInt($("#asiHcredEje").val()) / 2);
    //var telLab = Math.floor(parseInt($("#asiHcredLab").val()) / 2);

    var teo = 0;
    var eje = 0;
    //var lab = 0;

    var dataLista = $("#cursos-form").serializeArray();

    $.each(dataLista, function (i, field) {
        if (field.name.startsWith("tipo_")) {
            if ("C" === field.value) {
                teo++;
            }
            if ("A" === field.value) {
                eje++;
            }
        }
    });

    if ((telTeo >= teo && telEje >= eje) || $("#flagEstricto").val() === "U") {
        //if (telTeo >= teo && telEje >= eje && telLab >= lab) {

        var dataString = $("#cursos-form").serialize();
        jQuery.ajax({
            url: "CommonCursoDefinicionHorarioSave",
            type: "POST",
            data: dataString,
            success: function (data) {
                $("#horarioDummy").html(data);
            },
            async: false
        });

        var str = $("#result-status-div").html().trim();

        if (str === "Error")
        {
            $("#error-div").html("<p>" + $("#result-error-div").html().replaceAll("#", "</p>"));
            $("#error-modal").modal('show');
        }

        var hArray = $("#result-success-div").html().split(":");
        var table = $('#cursos-table').DataTable();
        table.cell($("#pos").val(), 3).data(hArray[0]);
        table.cell($("#pos").val(), 4).data(hArray[1]);
        table.cell($("#pos").val(), 5).data(hArray[2]);
        table.cell($("#pos").val(), 6).data(hArray[3]);
        table.cell($("#pos").val(), 14).data(hArray[4]);
        table.cell($("#pos").val(), 15).data(hArray[5]);

    } else {
        $("#error-div").html("<p> No corresponde a la definición del TEL </p>");
        $("#error-modal").modal('show');
    }
    if ($("#tipo").val() === 'T') {
        $("#cursos-form").attr("action", "CommonCursoDefinicionRefreshCursos?key=" + $("#key").val()).attr("target", "_self").submit();
    }
    return false;
}

function addProfesor()
{
    $("#rut-prof").val("");
    $("#rut-dv-prof").val("");
    $("#pat-prof").val("");
    $("#mat-prof").val("");
    $("#nom-prof").val("");
    $("#profesor-search-modal-div").html("");
    $("#profesor-search-modal").modal('show');
}

function addProfesorEstricto()
{
    $("#rut-prof").val("");
    $("#rut-dv-prof-estricto").val("");
    $("#pat-prof-estricto").val("");
    $("#mat-prof-estricto").val("");
    $("#nom-prof-estricto").val("");
    $("#profesor-estricto-search-modal-div").html("");
    $("#profesor-estricto-search-modal").modal('show');
}

function saveProfesor()
{    
    $("#save-profesor-modal").modal('hide');
    $("#profesor-search-modal").modal('hide');
    $("#modal-profesor").modal('hide');

    var dataString = $("#cursos-form").serialize();
    jQuery.ajax({
        url: "CommonCursoDefinicionProfesorSave",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#profesorDummy").html(data);
        },
        async: false
    });

    var table = $('#cursos-table').DataTable();
    table.cell($("#pos").val(), 5).data($("#profesorDummy").html());

    if ($("#tipo").val() === 'T') {
        $("#cursos-form").attr("action", "CommonCursoDefinicionRefreshCursos?key=" + $("#key").val()).attr("target", "_self").submit();
    }
}

function saveProfesorEstricto()
{
    $("#save-profesor-estricto-modal").modal('hide');
    $("#profesor-estricto-search-modal").modal('hide');
    $("#modal-profesor-estricto").modal('hide');

    var dataString = $("#cursos-form").serialize();
    jQuery.ajax({
        url: "CommonCursoDefinicionProfesorSave",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#profesorDummy").html(data);
        },
        async: false
    });

    var table = $('#cursos-table').DataTable();
    table.cell($("#pos").val(), 5).data($("#profesorDummy").html());

    if ($("#tipo").val() === 'T') {
        $("#cursos-form").attr("action", "CommonCursoDefinicionRefreshCursos?key=" + $("#key").val()).attr("target", "_self").submit();
    }
}

function addProfesorList(rutdv, pat, mat, nom)
{
    var optionsHor = "<option value=''></option>";

    $("#diamodDummy option").each(function () {
        optionsHor = optionsHor + "<option value='" + $(this).val() + "'>" + $(this).val() + "</option>";
    });

    var fila = getNewRowId();
    var col1 = '<td style="width: 5%"><input type="checkbox" id="prof_ck_' + fila + '" name="prof_ck_' + fila + '"/></td>';
    var col2 = '<td style="width: 15%"><input id="prof_' + fila + '" name="prof_' + fila + '" value="' + rutdv + '" readonly="readonly" class="form-control"/></td>';
    var col3 = '<td style="width: 70%">' + pat + '&nbsp;' + mat + '&nbsp;' + nom + '</td>';
    var col4 = '<td style="width: 10%"><select id="hor_' + fila + '" name="hor_' + fila + '" class="form-control">' + optionsHor + '</select></td>';
    var row = "<tr>" + col1 + col2 + col3 + col4 + "</tr>";

    $("#profesor-table").append(row);
    $("#profesor-search-modal").modal('hide');
}

function addProfesorEstrictoList(rutdv, pat, mat, nom)
{
    var row;
    $("#profesor-estricto-table tr").remove();
    $("#diamodEstrictoDummy option").each(function () {
        var fila = getNewRowId();
        var col1 = '<td style="width: 15%"><input id="prof_' + fila + '" name="prof_' + fila + '" value="' + rutdv + '" readonly="readonly" class="form-control"/></td>';
        var col2 = '<td style="width: 75%">' + pat + '&nbsp;' + mat + '&nbsp;' + nom + '</td>';
        var col3 = '<td style="width: 10%"><input id="hor_' + fila + '" name="hor_' + fila + '" value="' + $(this).val() + '" readonly="readonly" class="form-control"/></td>';
        row = "<tr>" + col1 + col2 + col3 + "</tr>";
        $("#profesor-estricto-table").append(row);
    });

    $("#profesor-estricto-search-modal").modal('hide');
}

function duplicateProfesor()
{
    var tdRut = $("#profesor-table tr:last").find('td:eq(1)').html();
    var tdNombre = $("#profesor-table tr:last").find('td:eq(2)').html();

    var firstIndexRut = tdRut.lastIndexOf("value=") + 7;
    var lastIndexRut = tdRut.lastIndexOf(" readonly=") - 1;

    var rutdv = tdRut.substring(firstIndexRut, lastIndexRut);
    var nombre = tdNombre.split("&nbsp;");

    addProfesorList(rutdv, nombre[0], nombre[1], nombre[2]);

}

function modalDelProfesor()
{
    $("#del-profesor-modal").modal('show');
}

function modalDelProfesorEstricto()
{
    $("#del-profesor-estricto-modal").modal('show');
}

function delProfesor()
{
    $("#del-profesor-modal").modal('hide');

    var tableControl = document.getElementById('profesor-table');
    $('input:checkbox:checked', tableControl).each(function () {
        $(this).closest('tr').remove();
    });
}

function delProfesorEstricto()
{
    $("#del-profesor-estricto-modal").modal('hide');

    $("#profesor-estricto-table tr").remove();
}


function modalSaveProfesor()
{
    $("#save-profesor-modal").modal('show');
}

function modalSaveProfesorEstricto()
{
    $("#save-profesor-estricto-modal").modal('show');
}

function searchProf()
{
    var valid = true;
    if ($("#rut-dv-prof").val() !== '')
    {
        if (!validateRutDv("rut-dv-prof", "rut-prof"))
        {
            valid = false;
        }
    }

    if (valid)
    {
        var rut = $("#rut-prof").val();
        var pat = $("#pat-prof").val();
        var mat = $("#mat-prof").val();
        var nom = $("#nom-prof").val();

        var dataString = {'key': $("#key").val(), 'rut': rut, 'paterno': pat, 'materno': mat, 'nombre': nom};
        jQuery.ajax({
            url: "CommonCursoDefinicionProfesorSearch",
            type: "POST",
            data: dataString,
            success: function (data) {
                $("#profesor-search-modal-div").html(data);
            },
            async: false
        });
    }
}

function searchProfEstricto()
{
    var valid = true;
    if ($("#rut-dv-prof-estricto").val() !== '')
    {
        if (!validateRutDv("rut-dv-prof-estricto", "rut-prof"))
        {
            valid = false;
        }
    }

    if (valid)
    {
        var rut = $("#rut-prof").val();
        var pat = $("#pat-prof-estricto").val();
        var mat = $("#mat-prof-estricto").val();
        var nom = $("#nom-prof-estricto").val();

        var dataString = {'key': $("#key").val(), 'rut': rut, 'paterno': pat, 'materno': mat, 'nombre': nom};
        jQuery.ajax({
            url: "CommonCursoDefinicionProfesorEstrictoSearch",
            type: "POST",
            data: dataString,
            success: function (data) {
                $("#profesor-estricto-search-modal-div").html(data);
            },
            async: false
        });
    }
}

function addAyudante()
{
    $("#rut-ayu").val("");
    $("#rut-dv-ayu").val("");
    $("#pat-ayu").val("");
    $("#mat-ayu").val("");
    $("#nom-ayu").val("");
    $("#ayudante-search-modal-div").html("");
    $("#ayudante-search-modal").modal('show');
}

function addAyudanteEstricto()
{
    $("#rut-ayu").val("");
    $("#rut-dv-ayu-estricto").val("");
    $("#pat-ayu-estricto").val("");
    $("#mat-ayu-estricto").val("");
    $("#nom-ayu-estricto").val("");
    $("#ayudante-estricto-search-modal-div").html("");
    $("#ayudante-estricto-search-modal").modal('show');
}

function saveAyudante()
{
    $("#save-ayudante-modal").modal('hide');
    $("#ayudante-search-modal").modal('hide');
    $("#ayudante-modal").modal('hide');

    var dataString = $("#cursos-form").serialize();
    jQuery.ajax({
        url: "CommonCursoDefinicionAyudanteSave",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#ayudanteDummy").html(data);
        },
        async: false
    });

    var table = $('#cursos-table').DataTable();
    table.cell($("#pos").val(), 6).data($("#ayudanteDummy").html());

    if ($("#tipo").val() === 'T') {
        $("#cursos-form").attr("action", "CommonCursoDefinicionRefreshCursos?key=" + $("#key").val()).attr("target", "_self").submit();
    }
}

function saveAyudanteEstricto()
{
    $("#save-ayudante-estricto-modal").modal('hide');
    $("#ayudante-estricto-search-modal").modal('hide');
    $("#ayudante-estricto-modal").modal('hide');

    var dataString = $("#cursos-form").serialize();

    jQuery.ajax({
        url: "CommonCursoDefinicionAyudanteSave",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#ayudanteDummy").html(data);
        },
        async: false
    });
    var table = $('#cursos-table').DataTable();

    table.cell($("#pos").val(), 6).data($("#ayudanteDummy").html());

    if ($("#tipo").val() === 'T') {
        $("#cursos-form").attr("action", "CommonCursoDefinicionRefreshCursos?key=" + $("#key").val()).attr("target", "_self").submit();
    }
}

function addAyudanteList(rutdv, pat, mat, nom)
{
    var optionsHor = "<option value=''></option>";

    $("#diamodAyuDummy option").each(function () {
        optionsHor = optionsHor + "<option value='" + $(this).val() + "'>" + $(this).val() + "</option>";
    });

    var fila = getNewRowId();

    var col1 = '<td style="width: 5%"><input type="checkbox" id="ayu_ck_' + fila + '" name="ayu_ck_' + fila + '"/></td>';
    var col2 = '<td style="width: 15%"><input id="ayu_' + fila + '" name="ayu_' + fila + '" value="' + rutdv + '" class="form-control" readonly="readonly"/></td>';
    var col3 = '<td style="width: 70%">' + pat + '&nbsp;' + mat + '&nbsp;' + nom + '</td>';
    var col4 = '<td style="width: 10%"><select id="horAyu_' + fila + '" name="horAyu_' + fila + '" class="form-control">' + optionsHor + '</select></td>';
    var row = "<tr>" + col1 + col2 + col3 + col4 + "</tr>";

    $("#ayudante-table").append(row);
    $("#ayudante-search-modal").modal('hide');
}

function addAyudanteEstrictoList(rutdv, pat, mat, nom)
{
    var row;
    $("#ayudante-estricto-table tr").remove();
    $("#diamodAyuEstrictoDummy option").each(function () {
        var fila = getNewRowId();

        var col1 = '<td style="width: 15%"><input id="ayu_' + fila + '" name="ayu_' + fila + '" value="' + rutdv + '" class="form-control" readonly="readonly"/></td>';
        var col2 = '<td style="width: 70%">' + pat + '&nbsp;' + mat + '&nbsp;' + nom + '</td>';
        var col3 = '<td style="width: 10%"><input id="horAyu_' + fila + '" name="horAyu_' + fila + '" value="' + $(this).val() + '" class="form-control" readonly="readonly"/></td>';
        row = "<tr>" + col1 + col2 + col3 + "</tr>";

        $("#ayudante-estricto-table").append(row);
    });
    $("#ayudante-estricto-search-modal").modal('hide');
}

function modalDelAyudante()
{
    $("#del-ayudante-modal").modal('show');
}

function modalDelAyudanteEstricto()
{
    $("#del-ayudante-estricto-modal").modal('show');
}

function delAyudante()
{
    $("#del-ayudante-modal").modal('hide');

    var tableControl = document.getElementById('ayudante-table');
    $('input:checkbox:checked', tableControl).each(function () {
        $(this).closest('tr').remove();
    });
}

function delAyudanteEstricto()
{
    $("#del-ayudante-estricto-modal").modal('hide');

    $("#ayudante-estricto-table tr").remove();
}

function modalSaveAyudante()
{
    $("#save-ayudante-modal").modal('show');
}

function modalSaveAyudanteEstricto()
{
    $("#save-ayudante-estricto-modal").modal('show');
}

function searchAyudante()
{
    var valid = true;
    if ($("#rut-dv-ayu").val() !== '')
    {
        if (!validateRutDv("rut-dv-ayu", "rut-ayu"))
        {
            valid = false;
        }
    }

    if (valid)
    {
        var rut = $("#rut-ayu").val();
        var pat = $("#pat-ayu").val();
        var mat = $("#mat-ayu").val();
        var nom = $("#nom-ayu").val();

        var dataString = {'key': $("#key").val(), 'rut': rut, 'paterno': pat, 'materno': mat, 'nombre': nom};
        jQuery.ajax({
            url: "CommonCursoDefinicionAyudanteSearch",
            type: "POST",
            data: dataString,
            success: function (data) {
                $("#ayudante-search-modal-div").html(data);
            },
            async: false
        });
    }
}

function searchAyudanteEstricto()
{
    var valid = true;
    if ($("#rut-dv-ayu-estricto").val() !== '')
    {
        if (!validateRutDv("rut-dv-ayu-estricto", "rut-ayu"))
        {
            valid = false;
        }
    }

    if (valid)
    {
        var rut = $("#rut-ayu").val();
        var pat = $("#pat-ayu-estricto").val();
        var mat = $("#mat-ayu-estricto").val();
        var nom = $("#nom-ayu-estricto").val();

        var dataString = {'key': $("#key").val(), 'rut': rut, 'paterno': pat, 'materno': mat, 'nombre': nom};
        jQuery.ajax({
            url: "CommonCursoDefinicionAyudanteEstrictoSearch",
            type: "POST",
            data: dataString,
            success: function (data) {
                $("#ayudante-estricto-search-modal-div").html(data);
            },
            async: false
        });
    }
}

$(document).ready(function () {
    $("#add-button").click(newCurso);
    $("#delete-button").click(deleteCursos);
    $("#print-button").click(printLista);
    $("#export-button").click(exportLista);
    $("#search-prof-button").click(searchProf);
    $("#search-prof-estricto-button").click(searchProfEstricto);
    $("#search-ayu-button").click(searchAyudante);
    $("#search-ayu-estricto-button").click(searchAyudanteEstricto);

    $('#save-new-curso-event').on('click', function (evt) {
        addCurso();
    });

    $('#save-modify-curso-event').on('click', function (evt) {
        modifyCurso();
    });

    $('#new-horario-event').on('click', function (evt) {
        addHorario();
    });
    $('#del-horario-event').on('click', function (evt) {
        modalDelHorario();
    });
    $('#save-horario-event').on('click', function (evt) {
        modalSaveHorario();
    });

    $('#new-profesor-event').on('click', function (evt) {
        addProfesor();
    });
    $('#new-profesor-estricto-event').on('click', function (evt) {
        addProfesorEstricto();
    });
    $('#add-profesor-event').on('click', function (evt) {
        duplicateProfesor();
    });
    $('#del-profesor-event').on('click', function (evt) {
        modalDelProfesor();
    });
    $('#del-profesor-estricto-event').on('click', function (evt) {
        modalDelProfesorEstricto();
    });
    $('#save-profesor-event').on('click', function (evt) {
        modalSaveProfesor();
    });
    $('#save-profesor-estricto-event').on('click', function (evt) {
        modalSaveProfesorEstricto();
    });
    $('#new-ayudante-event').on('click', function (evt) {
        addAyudante();
    });
    $('#new-ayudante-estricto-event').on('click', function (evt) {
        addAyudanteEstricto();
    });
    $('#del-ayudante-event').on('click', function (evt) {
        modalDelAyudante();
    });
    $('#del-ayudante-estricto-event').on('click', function (evt) {
        modalDelAyudanteEstricto();
    });
    $('#save-ayudante-event').on('click', function (evt) {
        modalSaveAyudante();
    });
    $('#save-ayudante-estricto-event').on('click', function (evt) {
        modalSaveAyudanteEstricto();
    });

    $('#asign').on('change', function () {
        var optionSelected = $(this).find("option:selected");
        var textSelected = optionSelected.text().toUpperCase();

        $('#elect').attr('readonly', true);
        $('#electivo').attr('readonly', true);
        $('#elect').val(0);
        $('#electivo').val('');
        if (textSelected.indexOf("ELECTIV") >= 0)
        {
            $('#elect').attr('readonly', false);
            //$('#electivo').attr('readonly', false);
            $('#elect').val('');
            //$('#electivo').val('');
        }
    });

    $("#elect").on('change', function () {
        var elect = this.value;
        var optionSelected = $("#asign").find("option:selected");
        var asign = optionSelected.val();

        var dataString = {"asign": asign, "elect": elect, "key": $("#key").val()};
        jQuery.ajax({
            url: "CommonCursoDefinicionGetElectivo",
            type: "POST",
            data: dataString,
            success: function (data) {
                $("#electivo-div").html(data);
            },
            async: false
        });

        $("#electivo").val($("#electivo-div").html());
    });

    jQuery('#coord').keypress(function (e) {
        var regex = new RegExp("^[a-zA-Z]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        } else
        {
            $('#coord').val('');
            return false;
        }
    });

    jQuery('#secc').keypress(function (e) {
        var regex = new RegExp("^[0-9]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        } else
        {
            $('#secc').val('');
            return false;
        }
    });

    jQuery('#cupo').keypress(function (e) {
        var regex = new RegExp("^[0-9]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        } else
        {
            $('#cupo').val('');
            return false;
        }
    });

    jQuery('#inicio').blur(function (e) {
        var str = $(this).val();
        var re = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
        if (re.test(str)) {
            var adata = str.split('/');
            var dd = parseInt(adata[0], 10);
            var mm = parseInt(adata[1], 10);
            var yyyy = parseInt(adata[2], 10);
            var xdata = new Date(yyyy, mm - 1, dd);

            if ((xdata.getFullYear() === yyyy) && (xdata.getMonth() === mm - 1) && (xdata.getDate() === dd)) {
                return true;
            } else {
                $('#inicio').val('');
                return false;
            }
        }
    });

    jQuery('#termino').blur(function (e) {
        var str = $(this).val();
        var re = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
        if (re.test(str)) {
            var adata = str.split('/');
            var dd = parseInt(adata[0], 10);
            var mm = parseInt(adata[1], 10);
            var yyyy = parseInt(adata[2], 10);
            var xdata = new Date(yyyy, mm - 1, dd);

            if ((xdata.getFullYear() === yyyy) && (xdata.getMonth() === mm - 1) && (xdata.getDate() === dd)) {
                return true;
            } else {
                $('#termino').val('');
                return false;
            }
        }
    });


    $("#cursos-table").dataTable({
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron resultados",
            "sInfo": "Mostrando desde _START_ hasta _END_ de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando desde 0 hasta 0 de 0 registros",
            "sInfoFiltered": "(filtrado de _MAX_ registros en total)",
            "sInfoPostFix": "",
            "sSearch": "Buscar:",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "Primero",
                "sPrevious": "Anterior",
                "sNext": "Siguiente",
                "sLast": "\u00daltimo"
            }
        },
        "aoColumns": [
            {"bSortable": false},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null}
        ],
        "aaSorting": [
            [0, "asc"]
        ], "aLengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
        "iDisplayLength": -1,
        "paging": false
    });

    var table = $('#cursos-table').DataTable();

    $('[data-toggle="tooltip"]').tooltip();

    $('#cursos-table tbody').on('click', 'td', function () {
        var fila = table.row(this).index();
        var col = table.cell(this).index().columnVisible;
        var rowTable = $(this).parent();
        var col1 = rowTable.find("td:eq(1)").text(); // get codigo
        var col2 = rowTable.find("td:eq(2)").text(); // get nombre
        $("#pos").val(fila);
        $("#tipo").val(rowTable.find("td:eq(13)").text().trim());
        var flagTeo = rowTable.find("td:eq(14)").text().trim();

        var flagEje = rowTable.find("td:eq(15)").text().trim();
        //var flagLab = rowTable.find("td:eq(16)").text().trim();
        var flagEstricto = rowTable.find("td:eq(17)").text().trim();
        $("#flagEstricto").val(flagEstricto);

        var espejo = false;
        if ($("#tipo").val() === 'E') {
            espejo = true;
        }

        var curso = col1 + ": " + col2;

        switch (col)
        {
            case 2:
                //showCurso();
                break;//Electivo
            case 3:
                if (!espejo)
                {
                    if (flagEstricto !== "S") {
                        showHorario(curso);
                    }
                }
                break;//Horario
            case 4:
                if (!espejo)
                {
                    if (flagEstricto !== "S") {
                        showHorario(curso);
                    }
                }
                break;//Horario
            case 5:
                if (!espejo)
                {
                    if (flagEstricto === "U") {
                        showProfesor(curso);
                    } else if (flagEstricto === "S") {
                        showProfesor(curso);
                    } else if (flagTeo === "S")
                    {
                        if (flagEstricto === "E") {
                            showProfesorEstricto(curso);
                        } else if (flagEstricto === "F") {
                            showProfesor(curso);
                        }
                    } else
                    {
                        showBlocked();
                    }
                }
                break;//Profesor
            case 6:
                if (!espejo)
                {
                    if (flagEstricto === "U") {
                        showAyudante(curso);
                    } else if (flagEstricto === "S") {
                        showAyudante(curso);
                    } else if (flagTeo === "S")
                    {
                        if (flagEstricto === "E") {
                            showAyudanteEstricto(curso);
                        } else if (flagEstricto === "F") {
                            showAyudante(curso);
                        }
                    } else
                    {
                        showBlocked();
                    }
                }
                break;//Ayudante
            case 7:
                showCurso();
                break;//Cupo
            case 8:
                showCurso();
                break;//Inscritos
            case 9:
                showCurso();
                break;//Diurno
            case 10:
                showCurso();
                break;//Vespertino
            case 11:
                showCurso();
                break;//Inicio
            case 12:
                showCurso();
                break;//Termino
        }
    });
    scrollToPos();
});



