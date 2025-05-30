function getActa(pos)
{
    $("#cursos-form").attr("action", "CommonActaRectificatoriaGetActa?pos=" + pos);
    $("#cursos-form").attr("target", "_self");
    $("#cursos-form").submit();
}

$(document).ready(function () {
    var msgError = $("#msg-dummy-error-div").text().replace(/(\r\n|\n|\r)/g, "");
    if (msgError !== '')
    {
        $("#msg-result-div").html("<div class='actionError'><ul><li><span>" + msgError + "</li></ul></span></div>");
        $("#result").modal('show');
    }

    var msgOK = $("#msg-dummy-ok-div").text().replace(/(\r\n|\n|\r)/g, "");
    if (msgOK !== '')
    {
        $("#msg-result-div").html("<div class='actionMessage'><ul><li><span>" + msgOK + "</li></ul></span></div>");
        $("#result").modal('show');
    }

    $("#cursos-table").dataTable({
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
                "sType": "asign_elect_coord_secc", "sClass": "text-right"
            },
            {
                "sType": null
            },
            {
                "sType": null
            }
        ]
    });
});
