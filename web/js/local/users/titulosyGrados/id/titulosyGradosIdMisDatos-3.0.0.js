

function saveMisDatosTyG() {
    $("#consulta-form").attr("action", "TitulosyGradosMisDatosSaveMisDatos");
    $("#consulta-form").submit();
}

$(document).ready(function () {


    //Handler
    $("#save-button").click(saveMisDatosTyG);
    //
    $("#consulta-form").validate({
        rules: {
            email: {
                required: true,
                multiemail: true
            }
        },
        messages: {
            email: {
                required: jQuery.validator.messages.required
            }
        }
    });
});