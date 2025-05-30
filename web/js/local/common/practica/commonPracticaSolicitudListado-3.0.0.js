
function viewSolicitud(pos)
{
    $("#pos").val(pos);
    $("#solicitudes-form").attr("action", "CommonPracticaViewSolicitud").attr("target", "_self").submit();
}

$(document).ready(function () {
    $("a").click(function () {
        var fieldName = $(this).attr("id");
        viewSolicitud(fieldName.substr(fieldName.indexOf("_") + 1));
    });

    $("#solicitudes-table").dataTable({
        "aLengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
        "iDisplayLength": -1,
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
            }
        ],
        "aaSorting": [
            [0, "asc"]
        ]
    });
});


