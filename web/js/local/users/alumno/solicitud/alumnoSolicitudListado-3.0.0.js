
function addSolicitud() {
    $("#solicitudes-form").attr("action", "AlumnoSolicitudGetNewSolicitud");
    $("#solicitudes-form").attr("target", "_self");
    $("#solicitudes-form").submit();
}

function remove() {
    $("#solicitudes-form").attr("action", "AlumnoSolicitudRemoveSolicitud");
    $("#solicitudes-form").attr("target", "_self");
    $("#solicitudes-form").submit();
}

function deleteSolicitudes() {
    if (anyChecked("solicitudes-form") === true) {
        $("#confirmacion").modal('show');
    } else {
        $("#aviso").modal('show');
    }
    return false;
}

function viewSolicitud(pos) {
    $("#pos").val(pos);
    $("#solicitudes-form").attr("action", "AlumnoSolicitudViewSolicitud");
    $("#solicitudes-form").attr("target", "_self");
    $("#solicitudes-form").submit();
}

function printSolicitudes() {
    if (anyChecked("solicitudes-form") === true) {
        $("#format").val("PDF");
        $("#solicitudes-form").attr("action", "CommonSolicitudPrintPrintSolicitud");
        $("#solicitudes-form").attr("target", "_blank");
        $("#solicitudes-form").submit();
        return true;
    } else {
        $("#aviso").dialog('show');
        return false;
    }
}

$(document).ready(function () {
    //Handlers
    $("#add-button").click(addSolicitud);
    $("#print-button").click(printSolicitudes);
    $("#del-button").click(deleteSolicitudes);
    $("a").click(function () {
        var fieldName = $(this).attr("id");
        viewSolicitud(fieldName.substr(fieldName.indexOf("_") + 1));
    });

    $("#solicitudes-table").dataTable({
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
            {"sType": "uk_date_short"},
            {"sType": null},
            {"sType": null},
            {"sType": null}
        ], "aaSorting": [
            [1, "desc"]
        ]
    });
});
