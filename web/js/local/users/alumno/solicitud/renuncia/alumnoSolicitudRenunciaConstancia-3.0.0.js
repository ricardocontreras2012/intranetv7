

function saveSolicitud() {
    if ($("#solicitud-form").validate().form() === true) {
        $("#solicitud-form").attr("action", "AlumnoSolicitudRenunciaConstancia").attr("target", "_self").submit();
        $("#id-body").html("");
        return true;
    } else {
        return false;
    }
}

$(document).ready(function () {
    $("#save-button").click(saveSolicitud);
    jQuery("textarea").change(function () {
        $(this).val($(this).val().trim());
    });

    $("#solicitud-form").validate({
        event: "blur",
        rules: {
            motivo: {
                required: true
            }
        }
    });
});
