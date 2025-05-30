

function exportCalidad() {
    $("#contentDisposition").val('attachment;filename="titulados_' + $("#agno").val() + '.xls"');
    $("#estadistica-form").attr("action", "CommonEstadisticasAlumnosxCalidad").submit();
}

$(document).ready(function () {
    //Handlers
    $("#excel-button").click(exportCalidad);
//
});