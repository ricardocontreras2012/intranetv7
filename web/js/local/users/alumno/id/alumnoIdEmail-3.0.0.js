
function save()
{
    if ($("#email-form").validate().form() === true)
    {
        $("#email-form").attr("action", "AlumnoLoginEmail");
        $("#email-form").attr("target", "_self");
        $("#email-form").submit();
    }
}

$(document).ready(function () {
    $("#save-button").click(save);
    $("#email-form").validate({
        rules: {
            email: {
                required: true,
                email: true
            },
            emailConfirm: {
                required: true,
                email: true,
                equalTo: "#email"
            }
        }
    });
});