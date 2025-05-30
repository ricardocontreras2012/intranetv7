
function exportPlanilla() {
    $("#lista-form").attr("action", "CommonAsistenciaExportPlanilla").submit();
}

$(document).ready(function () {
    //Handlers
    $("#export-button").click(exportPlanilla);
});
