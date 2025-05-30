
function exportActa() {
    $("#acta-form").attr("action", "CommonActaExport");
    $("#acta-form").submit();
}

function printActa() {
    $("#format").val("PDF");
    $("#acta-form").attr("action", "CommonActaPrint");
    $("#acta-form").attr("target", "_blank");
    $("#acta-form").submit();
}

$(document).ready(function () {
    //Handlers
    $("#print-button").click(printActa);
    $("#export-button").click(exportActa);   
});