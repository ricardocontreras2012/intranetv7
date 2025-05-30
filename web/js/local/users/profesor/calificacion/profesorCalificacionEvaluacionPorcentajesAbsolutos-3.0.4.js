

function forceNumber(row)
{
    var x = document.getElementById(row);
    x.value = x.value.replace('.', ',');
}

function sortEvaluacionesTable() {

    var rows = $('#evaluaciones-table tbody  tr').get();

    rows.sort(function (a, b) {
        var aAux = $(a).children('td').eq(0).html();
        var bAux = $(b).children('td').eq(0).html();

        var aAux2 = $(aAux).val();
        var bAux2 = $(bAux).val();

        var aAux3 = aAux2.split('_');
        var bAux3 = bAux2.split('_');

        var aAux4 = 100 * parseInt(aAux3[0]) + parseInt(aAux3[1]);
        var bAux4 = 100 * parseInt(bAux3[0]) + parseInt(bAux3[1]);

        if (aAux4 < bAux4) {
            return -1;
        }

        if (aAux4 > bAux4) {
            return 1;
        }

        return 0;

    });

    var table = $('#evaluaciones-table');
   
    //table.children('tbody').html("");
        
    $.each(rows, function (index, row) {
        $(table).children('tbody').append(row);        
    });
}

function agregar()
{        
    var medioCode = $("#tipoEval_list").val();
    if (medioCode > 0)
    {
        var n = 0;
        $("#evaluaciones-form :input").each(function () {
            var fieldName = $(this).attr("id");
            if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("rowCorrel_" + medioCode + "_")) {
                n++;
            }
        });
        n++;
        var fila = medioCode + "_" + n;
        var medioNew = $("#tipoEval_list option:selected").text();
        var porc = $("#porc").val();        
        
        var row = newRow(fila, medioNew, n, porc, '');
        $("#evaluaciones-tbody").append(row);

        sortEvaluacionesTable();
    }

    $("#new-evaluacion").modal('hide');
}

function addEvaluacion() {

    var medio = $("#medio-div").html().replace("tipoEvaluacion", "tipoEval_list").replace("tipoEvaluacion", "tipoEval_list");
    var porc = '<input id="porc" name="porc" value="0">';

    $("#new-evaluacion-div").html("<table><tr><td>Evaluación</td><td>" + medio + "</td></tr><tr><td>Porcentaje</td><td>" + porc + "</td></tr></table>");
    $("#new-evaluacion").modal('show');

    return false;
}


function emitir() {
    $("#evaluaciones-form").attr("action", "ProfesorEvaluacionEmitirActa");
    $("#evaluaciones-form").attr("target", "_self");
    $("#evaluaciones-form").submit();
}

function deleteEvaluaciones()
{
    $("#confirmacion-del").modal('show');
    return false;
}

function confirmarEmision()
{
    $("#confirmacion-acta").modal('show');
    return false;
}

function deleteRows() {
    //Eliminar filas
    $("#evaluaciones-form :input").each(function () {
        var fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("ck_")) {
            if ($("#" + fieldName).is(':checked')) {
                var row = "#" + fieldName.replace("ck_", "row_");
                $(row).remove();
            }
        }
    });
    reIndex();
    $("#confirmacion-del").modal('hide');
}

function reIndex()
{
    var newTable = '<tbody id="evaluaciones-tbody">';
    var tipo = 0;
    var correl = 0;
    var fila;
    var arr;
    var tipoAux;
    var correlAux;
    var oldId;
    var newId;
    var medioNew;
    var porc;

    $("#evaluaciones-form :input").each(function () {
        var fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined) {
            if (fieldName.startsWith("sort_")) {
                fila = $(this).val();
                arr = fila.split("_");
                tipoAux = parseInt(arr[0]);
                correlAux = parseInt(arr[1]);

                if (tipo !== tipoAux)
                {
                    correl = 0;
                    tipo = tipoAux;
                }
                correl++;

                oldId = tipo + "_" + correlAux;
                newId = tipo + "_" + correl;
                medioNew = $("#tipoEvaluacion option[value='" + tipo + "']").text();
                porc = $("#porc_" + oldId).val();
                newTable += newRow(newId, medioNew, correl, porc, '');
            }
        }
    });

    newTable += "</tbody>";
    $('#evaluaciones-table').html(newTable);
}


function newRow(fila, medio, n, porc, label)
{
    return '<tr id="row_' + fila + '"><td style="display: none"><input id="sort_' + fila + '" name="id_' + fila + '" type="text" value="' + fila + '"/></td><td><input type="checkbox" id="check_' + fila + '" name="check_' + fila + '"/></td><td>' + medio + '&nbsp;</td><td><input class="form-control col-2" readonly="readonly" id="rowCorrel_' + fila + '" name="rowCorrel_' + fila + '" type="text" style="text-align: right" value="' + n + '"/></td><td><input class="form-control col-12" type="text" style="text-align: left" id="label_' + fila + '" name="label_' + fila + '" value="' + label + '"/></td><td><input class="form-control col-2" type="text" style="text-align: right" id="porc_' + fila + '" name="porc_' + fila + '" value="' + porc + '" onkeyup="forceNumber(\'porc_' + fila + '\')"/></td><td></td></tr>';
}

function validateFull() {
    var individuales = true;
    var porc;
    //Validar individual
    $("#evaluaciones-form :input").each(function () {
        var fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("porc_")) {
            if ($(this).val() <= 0)
            {
                porc = $(this);
                individuales = false;
            }
        }
    });

    if (!individuales)
    {
        $("#msg-div").html("Ponderación debe ser >= 1%");
        porc.css('background-color', 'red');
        porc.css('color', 'white');
        $("#msg").modal('show');
        return false;
    }

    var sumMedios = 0;
    //Validar el total
    $("#evaluaciones-form :input").each(function () {
        var fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("porc_")) {
            sumMedios += parseFloat($(this).val().replace(",", "."));
        }
    });

    if (Math.abs(100 - sumMedios).toFixed(3) > 0.001) {
        $("#msg-div").html("Ponderaciones de evaluaciones no suman 100%");
        $("#msg").modal('show');
        errorPorcentajes();
        return false;
    }
    return true;
}

function errorPorcentajes() {
    $("#evaluaciones-form :input").each(function () {
        var fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("porc_")) {
            $(this).css('background-color', 'red');
            $(this).css('color', 'white');
        }
    });
}

function emitirActa() {
    $("#conf-acta-div").modal('show');
    return false;
}

function exportPlanilla() {
    $("#evaluaciones-form").attr("action", "ProfesorEvaluacionExportPlanilla");
    $("#evaluaciones-form").submit();
}

function saveForm() {
    if (validateFull()) {
        blockPage();
        $("#evaluaciones-form").attr("action", "ProfesorEvaluacionSavePorcentajesAbsolutos");
        $("#evaluaciones-form").submit();
    }
}

function changeModalidad() {
    $("#modalidad").val("R");
    $("#evaluaciones-form").attr("action", "ProfesorEvaluacionSetModalidad");
    $("#evaluaciones-form").submit();
}

function showNominaCurso(pos) {
    //if (validateFull()) {
    $("#pos").val(pos);
    var dataString = $("#evaluaciones-form").serialize();
    jQuery.ajax({
        url: "ProfesorEvaluacionSavePorcentajesAbsolutos",
        type: "POST",
        data: dataString,
        async: false
    });
    $("#evaluaciones-form").attr("action", "ProfesorEvaluacionGetAlumnoEvaluacion");
    $("#evaluaciones-form").submit();
    //}
}

$(document).ready(function () {
    unblockPage();
});
