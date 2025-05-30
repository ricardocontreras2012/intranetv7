

function addAsistencia() {
    $("#asistencias-form").attr("action", "CommonAsistenciaNewAsistencia");
    $("#asistencias-form").attr("target", "_self");
    $("#asistencias-form").submit();
}

function viewPlanilla() {
    $("#asistencias-form").attr("action", "CommonAsistenciaGetPlanilla");
    $("#asistencias-form").attr("target", "_self");
    $("#asistencias-form").submit();
}

function remove()
{
    $("#asistencias-form").attr("action", "CommonAsistenciaRemoveAsistencia");
    $("#asistencias-form").attr("target", "_self");
    $("#asistencias-form").submit();
}

function deleteAsistencias() {
    if (anyChecked("asistencias-form") === true) {
        $("#confirmacion").modal('show');
    } else {
        $("#aviso").modal('show');
    }
    return false;
}

function viewAsistencia(pos) {
    $("#pos").val(pos);
    $("#asistencias-form").attr("action", "CommonAsistenciaGetAsistencia");
    $("#asistencias-form").attr("target", "_self");
    $("#asistencias-form").submit();
}

$(document).ready(function () {

    //Handlers
    $("#add-button").click(addAsistencia);
    $("#delete-button").click(deleteAsistencias);
    $("#planilla-button").click(viewPlanilla);
    $("a").click(function () {
        var field_name = $(this).attr("id");
        viewAsistencia(field_name.substr(field_name.indexOf("_") + 1));
    });
    //
    $("#aviso").modal('hide');
    $("#confirmacion").modal('hide');

    $("#asistencias-table").dataTable({
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
            {
                "bSortable": false
            },
            {
                "sType": "uk_date_short"
            }
        ],
        "aaSorting": [
            [1, "desc"]
        ]
    });
});