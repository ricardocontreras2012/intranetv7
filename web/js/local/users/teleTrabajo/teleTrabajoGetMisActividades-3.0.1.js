function getTareas(pos)
{
    $("#pos").val(pos);
    var dataString = $("#actividades-form").serialize();
    $("#main-content-iframe", window.parent.document).attr("src", "TeleTrabajoGetMisTareas?" + dataString);
}

function confirmacionAceptarActividad()
{
    $("#tituloModalAceptacion").text("Mensaje de compromiso");
    $("#mensajeConfirmacionAceptacion").text("Si usted selecciona \"acepto\", se compromete a realizar la actividad descrita en la fecha estupilada. ¿Esta seguro?");
    $("#AvisoConfirmacionAceptacion").modal('show');
}

function aceptarActividad()
{
    $("#estado").val("A");
    $("#actividades-form").attr("action", "TeleTrabajoUpdateEstadoMiActividad").submit();
}

function confirmacionRechazarActividad()
{
    $("#tituloModalRechazo").text("Confirmación");
    $("#mensajeConfirmacionRechazo").text("Despues de rechazar esta actividad, usted no podra realizar ningun otro cambio en esta. ¿Esta seguro de que desea continuar?");
    $("#AvisoConfirmacionRechazo").modal('show');
}

function rechazarActividad()
{
    $("#estado").val("R");
    $("#actividades-form").attr("action", "TeleTrabajoUpdateEstadoMiActividad").submit();
}

function enviarActividad(pos)
{
    $("#pos").val(pos);
    $("#actividades-form").attr("action", "TeleTrabajoEnviarRevisionMiActividad").submit();
}

$(document).ready(function () {
    $("#check-button").click(confirmacionAceptarActividad);
    $("#close-button").click(confirmacionRechazarActividad);
    $("#botonAceptacion").click(aceptarActividad);
    $("#botonRechazo").click(rechazarActividad);

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
            {"sType": null},
            {"bSortable": false}
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
