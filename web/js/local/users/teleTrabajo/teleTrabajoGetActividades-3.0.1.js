function getTareas(pos)
{
    $("#pos").val(pos);
    $("#actividades-form").attr("action", "TeleTrabajoGetTareas").attr("target", "_self").submit();
}

function deleteActividad() {
    if (anyChecked("actividades-form") === true) {
        $("#confirmacion").modal('show');
    } else {
        $("#aviso").modal('show');
    }
    return false;
}

function removeActividades() {
    $("#actividades-form").attr("action", "TeleTrabajoRemoveActividad").submit();
}

function newActividad()
{
    $("#actividades-form").attr("action", "TeleTrabajoAsignarTeletrabajo").submit();
}
    
function terminarActividad()
{
    $("#estado").val("T");
    $("#actividades-form").attr("action", "TeleTrabajoUpdateEstadoActividad").submit();
}

function noTerminarActividad()
{
    $("#estado").val("E");
    $("#actividades-form").attr("action", "TeleTrabajoUpdateEstadoActividad").submit();
}


$(document).ready(function () {
    $("#add-button").click(newActividad);
    $("#delete-button").click(deleteActividad);
    $("#check-button").click(terminarActividad);
    $("#close-button").click(noTerminarActividad);

    $("a").click(function () {
        var fieldName = $(this).attr("id");
        getTareas(fieldName.substr(fieldName.indexOf("_") + 1));
    });
    
    $("#actividades-table").dataTable({
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
});