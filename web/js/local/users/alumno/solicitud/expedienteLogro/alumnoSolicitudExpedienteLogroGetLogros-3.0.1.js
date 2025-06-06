function getDoc(pos) {
    $("#pos").val(pos);
    $("#solicitudes-form").attr("action", "AlumnoSolicitudExpedienteNew");
    $("#solicitudes-form").attr("target", "_self");
    $("#solicitudes-form").submit();
}

$(document).ready(function () {
    //Handlers
    $("a").click(function () {
        var fieldName = $(this).attr("id");
        getDoc(fieldName.substr(fieldName.indexOf("_") + 1));
    });
});