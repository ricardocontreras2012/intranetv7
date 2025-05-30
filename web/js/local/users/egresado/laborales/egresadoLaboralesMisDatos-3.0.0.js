

function addReport() {
    $("#ficha-form").attr("action", "EgresadoLaboralesNewLaboral");
    $("#ficha-form").attr("target", "_self");
    $("#ficha-form").submit();
}

function viewReport(sesionVal) {
    $("#sesionReporte").val(sesionVal);
    $("#reportes-form").attr("action", "EgresadoLanboralesViewLaboral");
    $("#reportes-form").attr("target", "_self");
    $("#reportes-form").submit();
}

$(document).ready(function () {
    //Handlers
    $("#add-button").click(addReport);
});