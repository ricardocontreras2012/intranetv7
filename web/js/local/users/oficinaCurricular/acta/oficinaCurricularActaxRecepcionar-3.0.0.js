

function executeSearch() {
    $("#actas-form").attr("action", 'OficinaCurricularActaRecepcionGetActasxSemAgno');
    $("#actas-form").attr("target", "_self");
    $("#actas-form").submit();
}

function executeAction(posValue) {
    $("#actas-form").attr("action", 'OficinaCurricularActaRecepcionarActas?pos=' + posValue + '&key=' + $("#key").val());
    $("#actas-form").attr("target", "_blank");
    $("#actas-form").submit();
}

function executeBatch() {
    let fila;
    $("#actas-form input[type=checkbox]").each(function () {
        if ($(this).attr("id").startsWith("ck")) {
            if ($(this).is(":checked")) {
                fila = $(this).attr("id");
                executeAction(fila.substr(fila.indexOf("_") + 1));
            }
        }
    });
}

$(document).ready(function () {

    //Handlers
    $("#print-button").click(executeBatch);
    $("#search-button").click(executeSearch);

    $("#actas-table").dataTable({
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
        "aaSorting": [
            [ 0, "asc" ]
        ],
        "iDisplayLength": 20
    });
});