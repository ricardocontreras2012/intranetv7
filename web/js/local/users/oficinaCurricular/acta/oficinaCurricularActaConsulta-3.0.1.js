

function executeSearch() {
    $("#actas-form").attr("action", 'OficinaCurricularActaConsultarGetActasxSemAgno');
    $("#actas-form").attr("target", "_self");
    $("#actas-form").submit();

}

$(document).ready(function () {
    //Handlers
    //$("#print-button").click(executePrint);
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
            {"bSortable": false},
            {"sType": "numeric"},
            {"sType": "string"},
            {"sType": "string"},
            {"sType": "string"},
            {"sType": "string"}
        ], "aaSorting": [
            [0, "asc"]
        ], "iDisplayLength": 20
    });
});
