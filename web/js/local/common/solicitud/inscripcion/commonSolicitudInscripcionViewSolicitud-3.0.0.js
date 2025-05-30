
function grabarResolucion() {
    if ($("#resolucion").val() !== " ")
    {
        $("#solicitud-form").attr("action", "CommonSolicitudSaveResolucion?resolucion=" + $("#resolucion").val());
        $("#solicitud-form").attr("target", "_self");
        $("#solicitud-form").submit();
        return true;
    } else
    {
        return false;
    }
}

$(document).ready(function () {
    $("#save-button").click(grabarResolucion);
});
