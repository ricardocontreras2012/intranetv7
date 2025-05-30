

function addReport() {
    $("#reportes-form").attr("action", "EgresadoEstudiosNewEstudio");
    $("#reportes-form").attr("target", "_self");
    $("#reportes-form").submit();
}

$(document).ready(function () {
    //Handlers
    $("#add-button").click(addReport);

});