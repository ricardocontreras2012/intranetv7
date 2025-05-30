
function setEmail() {
    $("#email-form").attr("action", $("#actionDummy").val()).submit();
}

$(document).ready(function () {
    //Handler
    $("#save-button").click(setEmail);
    //
    $("#email-form").validate({
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