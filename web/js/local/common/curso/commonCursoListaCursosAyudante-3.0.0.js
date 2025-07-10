
function printLista() {
    $("#cursos-form").attr("action", "CommonProfesorExportCargaHistorica?format=PDF").attr("target", "_blank").submit();
}

function exportLista() {
    $("#cursos-form").attr("action", 'CommonProfesorExportCargaHistorica?format=XLSX&contentDisposition=attachment;filename="carga_historica.xlsx"').submit();
}

function showDetail(posVal) {
    $("#pos").val(posVal);
    $("#cursos-form").attr("action", "CommonCursoGetCursoActual").submit();
}

$(document).ready(function () {
    //Handlers
    $("#print-button").click(printLista);
    $("#export-button").click(exportLista);
    $("a").click(function () {
        const field_name = $(this).attr("id");
        showDetail(field_name.substr(field_name.indexOf("_") + 1));
    });

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
                "sType": "asign_elect"
            },
            {
                "sType": "coord_secc"
            },
            {
                "sType": "sem_agno"
            },
            {
                "sType": null
            }
        ],
        "aaSorting": [
            [2, "desc" ],
            [0, "asc" ],
            [1, "asc" ]
        ], "iDisplayLength": -1,
        "aLengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ]
    });
    //
});