
function exportActas() {
    $("#actas-form").attr("action", "CommonActaActasCarreraExport").attr("target", "_blank").submit();
}

function getActa(posValue) { 
    $("#pos").val(posValue);
    $("#actas-form").attr("action", "CommonActaGetActa").attr("target", "_blank").submit();
}

$(document).ready(function() {
    $("#export-button").click(exportActas);
    
    //Handler
    $("a").click(function () {
        var fieldName = $(this).attr("id");  
        getActa(fieldName.substr(fieldName.indexOf("_") + 1));
    });
    
    $("#actas-table").dataTable({
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
        "aoColumns": [{
                "sType": "asign_elect_coord_secc", "sClass":"text-right"
            }, null, null, null, null, null]
    });
});