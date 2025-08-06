
function exportLista() {
    $("#cursos-form").attr("action", 'CommonCursoDefinicionExport').attr("target", "_blank").submit();
}

function showDetail(posVal) {
    $("#pos").val(posVal);
    $("#cursos-form").attr("action", "CommonCursoGetCursoActualxAgnoSem");
    $("#cursos-form").attr("target", "_blank").submit();
}

$(document).ready(function () {
    //Handlers
    $("a").click(function () {
        const field_name = $(this).attr("id");
        showDetail(field_name.substr(field_name.indexOf("_") + 1));
    });
    
    $("#export-button").click(exportLista);

    $("#cursos-table").dataTable({"aLengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
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
                "sType": false
            },
            {
                "sType": false
            },
            {
                "sType": false
            },
            {
                "sType": false
            }
        ],
        "aaSorting": [
            [ 1, "asc" ]
        ],
        "iDisplayLength": -1
    });
    //
});