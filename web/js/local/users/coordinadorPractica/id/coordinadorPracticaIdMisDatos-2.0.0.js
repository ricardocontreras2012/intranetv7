

function save() {
    $("#consulta-form").attr("action", "ConsultaMisDatosSaveMisDatos");
    $("#consulta-form").submit();
}

$(document).ready(function () {


    //Handler
    $("#save-button").click(save);
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

    loadIframeCentral();
});