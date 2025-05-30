

function printLista() {
    $("#cursos-form").attr("action", 'CommonCursoDefinicionPrint?key=' + $("#keyDummy").val());
    $("#cursos-form").attr("target", "_blank").submit();
}

function exportLista() {
    $("#cursos-form").attr("action", 'CommonCursoDefinicionExport?key=' + $("#keyDummy").val());
    $("#cursos-form").attr("target", "_blank").submit();
}

$(document).ready(function () {
    $("#print-button").click(printLista);
    $("#export-button").click(exportLista);

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
            {
                "sType": "asign_elect_coord_secc", "sClass":"text-right"
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
            }
        ],
        "aLengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
        "iDisplayLength": -1
    });
});