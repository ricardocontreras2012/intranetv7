
function exportLista() {
    $("#nomina-form").attr("action", 'CommonMatriculaExportNomina?key=' + $("#key").val());
    $("#nomina-form").attr("target", "_blank").submit();
}

$(document).ready(function () {
//Handlers
    $("#export-button").click(exportLista);
});

