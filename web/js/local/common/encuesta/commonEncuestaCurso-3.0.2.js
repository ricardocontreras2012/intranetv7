
function printEncuesta() {
    $("#encuesta").attr("action", "CommonEncuestaPrint");
    $("#encuesta").attr("target", "_blank");
    $("#encuesta").submit();
}

function exportEncuesta() {
    $("#encuesta").attr("action", "CommonEncuestaExport");
    $("#encuesta").attr("target", "_blank");
    $("#encuesta").submit();
}

$(document).ready(function () {
    //Handlers
    $("#print-button").click(printEncuesta);
    $("#export-button").click(exportEncuesta);
});