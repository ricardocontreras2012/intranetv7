

function submitLogin() {
    $("#userType").val($("#usuarios option:selected").val());
    if ($("#login-form").validate().form() === true) {
        $.blockUI();
        $("#login-form").attr("action", "CommonPasswordAskNewPassword").submit();

        return true;
    }
    return false;
}


$(document).ready(function () {
    //Handler
    $("#send-button").click(submitLogin);    
        
    $("#rutdv").keyup(function () {
        var rut = formatear($(this).val(), true);
        $(this).val(rut);
    });
    //
    jQuery.validator.addMethod("rutdv", function (value, element) {
        var regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        return this.optional(element) || (regExp.test(jQuery.trim(value)) && validateRutDv("rutdv", "rut"));
    }, "Rut-Dv inválido.");

    $("#login-form").validate({
        rules: {
            usuarios: {required: true},
            rutdv: {
                required: true,
                rutdv: true
            }
        },
        messages: {
            usuarios: {
                required: jQuery.validator.messages.required
            },
            rutdv: {
                required: jQuery.validator.messages.required
            }
        }
    });
});