

function receiveSolicitud(pos) {
    $("#pos").val(pos);
    $("#solicitudes-form").attr("action", "OficinaCurricularSolicitudEnableReceiveSolicitud");
    $("#solicitudes-form").attr("target", "_self");
    $("#solicitudes-form").submit();
}

function informSolicitud(pos) {
    $("#pos").val(pos);
    $("#solicitudes-form").attr("action", "OficinaCurricularSolicitudEnableInformSolicitud");
    $("#solicitudes-form").attr("target", "_self");
    $("#solicitudes-form").submit();
}

$(document).ready(function () {


    //Handlers
    $("#receive-button").click(receiveSolicitud);
    $("#inform-button").click(informSolicitud);
    $("a").click(function () {
        /* OJO PIOJO
         var field_name = $(this).attr("id");
         if('<s:property value="estado"/>' ==='GEN')
         {
         receiveSolicitud(field_name.substr(field_name.indexOf("_")+1));
         }
         else
         {
         informSolicitud(field_name.substr(field_name.indexOf("_")+1));
         }
         */
    });
    //

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
            {
                "sType": null
            },
            {
                "sType": null
            },
            {
                "sType": null
            },
            {
                "sType": null
            },
            {
                "sType": "uk_date_short"
            },
            {
                "sType": null
            }
        ],
        "aaSorting": [
            [ 0, "asc" ]
        ]
    });
});