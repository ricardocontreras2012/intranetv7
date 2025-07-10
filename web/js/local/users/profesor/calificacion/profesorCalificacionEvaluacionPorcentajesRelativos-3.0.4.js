
function confirmarEmision(){
    $("#confirmacion-acta").modal('show');
    return false;
}

function emitir() {
    $("#evaluaciones-form").attr("action", "ProfesorEvaluacionEmitirActa");
    $("#evaluaciones-form").attr("target", "_self");
    $("#evaluaciones-form").submit();
}

function changeModalidad() {
    $("#modalidad").val("A");
    $("#evaluaciones-form").attr("action", "ProfesorEvaluacionSetModalidad");
    $("#evaluaciones-form").submit();
}

function exportPlanilla() {
    $("#evaluaciones-form").attr("action", "ProfesorEvaluacionExportPlanilla");
    $("#evaluaciones-form").submit();
}

function saveForm() {
    if (validateFull()) {
        $("#evaluaciones-form").attr("action", "ProfesorEvaluacionSavePorcentajesRelativos");
        $("#evaluaciones-form").submit();
    }
}

function showNominaCurso(pos) {
    if (validateFull()) {
        $("#pos").val(pos);
        const dataString = $("#evaluaciones-form").serialize();
        jQuery.ajax({
            url: "ProfesorEvaluacionSavePorcentajesRelativos",
            type: "POST",
            data: dataString,
            async: false
        });

        $("#evaluaciones-form").attr("action", "ProfesorEvaluacionGetAlumnoEvaluacion");
        $("#evaluaciones-form").submit();
    }
}

function addTipoEvaluacion() {
    const tipos = $("#tipos").html();
    const htmlNew = '<form id="new-evaluaciones-form" onsubmit="return false;">' +
        '<table>' +
        '<tr style="padding:50px;">' +
        '<td>Medio de Evaluación&nbsp;&nbsp;</td>' +
        '<td>% Nota Final&nbsp;&nbsp;</td>' +
        '<td># Evaluaciones&nbsp;&nbsp;</td>' +
        '<td></td><td></td>' +
        '</tr>' +
        '<tr>' +
        '<td>' + tipos +
        '</td>' +
        '<td><input id="porc" name="porc" type="text" size="4"/></td>' +
        '<td><input id="cant" name="cant" type="text" size="4"/></td>' +
        '<td><input id="porcTotal" name="porcTotal" type="hidden" value="0"/></td>' +
        '<td><div class="buttons-div"><button id="add-button" title="Agregar Tipo de Evaluación" type="button" class="btn btn-default" data-toggle="tooltip" onclick="getTipoEvaluacion();"><span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs">Agregar</span></button></div></td>' +
        '</tr>' +
        '</table>' +
        '</form>';

    $("#new").html(htmlNew);
    $("#new").show("slow");
}

function addTbody(tipoEvaluacion, codigo, porc) {
    addTableTbody($('#eval'), tipoEvaluacion, codigo, porc);
}

function addTableTbody(jQtable, tipoEvaluacion, codigo, porc) {
    jQtable.each(function () {
        const tBody =
            '<table width="100%" style="background-color:#BDE5F8;">' +
            '<tr><td style="text-align: center; background-color: #679FD2; color:white" valign="top" colspan="2">' + tipoEvaluacion + '</td></tr>' +
            '<tr>' +
            '<td style="width:30%" colspan="2">' +
            '<div class="container container-menu"><div class="row"><div id="justified-button-bar" class="col-lg-12">' +
            '<div class="btn-group">' +
            '<div class="btn-group">' +
            '<button id="add-button" title="Nueva" type="button" class="btn btn-default" data-toggle="tooltip" onclick="addRow(' + codigo + ',\'' + tipoEvaluacion + '\',\'\'); return false;">' +
            '<span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs">Nueva Evaluación</span>' +
            '</button></div> ' +
            '<div class="btn-group">' +
            '<button id="del-button" title="Eliminar" type="button" class="btn btn-default" data-toggle="tooltip" onclick="deleteEvaluaciones(' + codigo + '); return false;">' +
            '<span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs">Eliminar</span>' +
            '</button></div></div></div></div></div>' +
            '</td>' +
            '</tr>' +
            '<tr>' +
            '<td style="width:20%">' +
            '<input name="nom_tbody_' + codigo + '" id="nom_tbody_' + codigo + '" type="hidden" value="' + tipoEvaluacion + '"/>' +
            '% de la Nota Final&nbsp;<input class="" name="porcTbody_' + codigo + '" id="porcTbody_' + codigo + '" type="text" value="' + porc + '" size="3" style="text-align: right"/>' +
            '</td>' +
            '<td style="width:80%">' +
            '</td>' +
            '</tr>' +
            '</table>' +
            '<table id="evaluaciones-table_' + codigo + '" width="100%" style="background-color:#BDE5F8;">' +
            '<tbody id="tbody_' + codigo + '"></tbody></table>';

        $(this).append(tBody);
        $("#porcTbody_" + codigo).rules("add", {
            required: true,
            min: 1,
            max: 100,
            messages: {
                required: jQuery.validator.messages.required,
                min: jQuery.validator.messages.min(1),
                max: jQuery.validator.messages.max(100)
            }});
    });
}

const addRow = function (codigo, descripcion) {
    const porc = 0;

    let rowNum = 0;
    let row = "";
    $('#evaluaciones-table_' + codigo).each(function () {
        rowNum = $('#evaluaciones-table_' + codigo + " > tbody > tr").length + 1;
        row = '<tr id="row_' + codigo + '_' + rowNum + '"><td style="width:5%"><input type="checkbox" id="ck_' + codigo + '_' + rowNum + '" name="ck_' + codigo + '_' + rowNum + '"/></td><td style="width:40%">' + descripcion + '<input readonly="readonly" id="rowCorrel_' + codigo + '_' + rowNum + '" name="rowCorrel_' + codigo + '_' + rowNum + '"type="text" size="3" style="text-align: right" value="' + rowNum + '"/></td><td><s:text name="label.porcentaje"/><input class="" type="text" size="3" style="text-align: right" id="porc_' + codigo + '_' + rowNum + '" name="porc_' + codigo + '_' + rowNum + '" value="' + porc + '"/>%</td><td></td></tr>';
        $("#tbody_" + codigo).append(row);

        $("#porc_" + codigo + "_" + rowNum).rules("add", {
            required: true,
            min: 1,
            max: 100,
            messages: {
                required: jQuery.validator.messages.required,
                min: jQuery.validator.messages.min(1),
                max: jQuery.validator.messages.max(100)
            }
        });

    });

    const porcAux = Math.floor(100 / rowNum);
    for (let i = 1; i < rowNum; i++) {
        $("#porc_" + codigo + "_" + i).val(porcAux);
    }
    $("#porc_" + codigo + "_" + rowNum).val(100 - (porcAux * (rowNum - 1)));
};

function getTipoEvaluacion() {
    const tipoEvaluacion = $("#tipoEvaluacion :selected").text();
    const codigo = $("#tipoEvaluacion").val();
    const cantidad = $("#cant").val();
    const porc = $("#porc").val();
    const total = $("#porcTotal").val();

    if (codigo > 0 && cantidad > 0 && porc > 0) {
        if (parseFloat(porc) + parseFloat(total) <= 100) {
            $('#tipoEvaluacion').find("option[value='" + codigo + "']").remove();
            addTbody(tipoEvaluacion, codigo, porc);

            for (let i = 1; i <= cantidad; i++) {
                addRow(codigo, tipoEvaluacion);
            }
            $("#cant").val("");
            $("#porc").val("");
            $("#new").hide();
            $("#porcTotal").val(parseFloat(porc) + parseFloat(total));
        } else {
            $("#msg-div").html("ponderación excede el 100%");
            $("#msg").modal('show');
            return false;
        }
    } else {
        return false;
    }

    return false;
}

function validateFull() {
    let sumMedios = 0;
    const medios = [];
    let index = 0;

    //Validar la ponderación de los medios
    $("#evaluaciones-form :input").each(function () {
        const fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("porcTbody_")) {
            sumMedios += parseFloat($(this).val());
            medios[index++] = fieldName.substr(10, fieldName.length);//11=largo de "porcTbody_"
        }
    });
    if (sumMedios <= 100.001 && sumMedios >= 99.999) {
        //Validar la ponderación interna
        for (let i = 0; i < index; i++) {
            sumMedios = 0;
            let esta = false;
            $("#evaluaciones-form :input").each(function () {
                const fieldName = $(this).attr("id");
                if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("porc_" + medios[i])) {
                    sumMedios += parseFloat($(this).val());
                    esta = true;
                }
            });
            if (esta && (sumMedios > 100.001 || sumMedios < 99.999)) {
                errorRowPorc(medios[i]);
                $("#msg-div").html("Ponderaciones de " + $("#nom_tbody_" + medios[i]).val() + " no suman 100%");
                $("#msg").modal('show');
                return false;
            }
        }
    } else {
        errorBodyPorc();

        $("#msg-div").html("Ponderaciones de medios evaluativos no suman 100%");
        $("#msg").modal('show');
        return false;
    }
    return true;
}

function errorBodyPorc() {
    $("#evaluaciones-form :input").each(function () {
        const fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("porcTbody_")) {
            $(this).attr('class', 'errorPond');
        }
    });
}

function errorRowPorc(type) {
    $("#evaluaciones-form :input").each(function () {
        const fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("porc_" + type)) {
            $(this).attr('class', 'errorPond');
        }
    });
}

function deleteEvaluaciones(tipo) {
    $("#tipoTmp").val(tipo);
    $("#confirmacion-del").modal('show');
    return false;
}

function deleteRows() {

    $("#confirmacion-del").modal('hide');
    const type = $("#tipoTmp").val();
    let initialRows = parseInt($("#num_rows_" + type).val(), 10);

    //Eliminar filas
    $("#evaluaciones-form :input").each(function () {
        const fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("ck_" + type + "_")) {
            if ($("#" + fieldName).is(':checked')) {
                const row = "#" + fieldName.replace("ck_", "row_");
                $(row).remove();
                initialRows -= 1;
            }
        }
    });

    //Reasignar numeros de evaluaciones-form en la cabecera y porcentajes en cada fila de evaluacion
    $("#evaluaciones-form :input").each(function () {
        const fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("num_rows_" + type)) {
            $(this).val(parseInt(initialRows, 10));
        }
    });

    //Reasignar los identificadores de fila (1ra parte)
    let index_porc = 1;
    let index_check = 1;
    let index_rowCorrel = 1;
    $("#evaluaciones-form :input").each(function () {
        const fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined) {
            if (fieldName.startsWith("porc_" + type + "_")) {
                $(this).attr('id', 'porc_' + type + '_' + index_porc);
                $(this).attr('name', 'porc_' + type + '_' + index_porc);
                index_porc += 1;
            }

            if (fieldName.startsWith("ck_" + type + "_")) {
                $(this).attr('id', 'ck_' + type + '_' + index_check);
                $(this).attr('name', 'ck_' + type + '_' + index_check);
                index_check += 1;

            }
            if (fieldName.startsWith("rowCorrel_" + type + "_")) {
                $(this).attr('id', 'rowCorrel_' + type + '_' + index_rowCorrel);
                $(this).attr('name', 'rowCorrel_' + type + '_' + index_rowCorrel);
                $(this).val(index_rowCorrel);
                index_rowCorrel += 1;
            }
        }
    });

    //Reasignar los identificadores de fila (2da parte)

    let index_row = 1;
    $("#evaluaciones-form tr").each(function () {
        const fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("row_" + type + "_")) {
            $(this).attr('id', 'row_' + type + '_' + index_row);
            $(this).attr('name', 'row_' + type + '_' + index_row);
            index_row += 1;
        }
    });

    const porc = Math.round(100 / initialRows);
    for (let i = 1; i < initialRows; i++) {
        $("#porc_" + type + "_" + i).val(porc);
    }
    $("#porc_" + type + "_" + initialRows).val(100 - (porc * (initialRows - 1)));
}

$(document).ready(function () {

    $("#new").hide();

    $.validator.addMethod("selectOption",
            function (value, element) {
                return this.optional(element) || ((value !== '0'));
            }
    , "Seleccione Medio de Evaluación");

    $("#new-evaluaciones-form").validate({
        event: "blur",
        rules: {
            tipoEvaluacion: {required: true, selectOption: true},
            porc: {required: true, min: 1, max: 100},
            cant: {required: true, min: 1}
        },
        messages: {
            porc: {required: jQuery.validator.messages.required, min: jQuery.validator.messages.min(1), max: jQuery.validator.messages.max(100)},
            cant: {required: jQuery.validator.messages.required, min: jQuery.validator.messages.min(1)}
        }
    });

    $("#evaluaciones-form").validate({
        event: "blur",
        rules: {
        },
        messages: {
        }
    });

    $("#evaluaciones-form :input").each(function () {
        const fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("porc_")) {
            $(this).rules("add", {
                required: true,
                min: 1,
                max: 100,
                messages: {
                    required: jQuery.validator.messages.required,
                    min: jQuery.validator.messages.min(1),
                    max: jQuery.validator.messages.max(100)
                }});
        }
    });
});