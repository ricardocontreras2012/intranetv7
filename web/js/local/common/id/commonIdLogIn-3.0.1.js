
function submitLogin() {
    $("#userType").val($("#usuarios option:selected").val());
    if ($("#login-form").validate().form() === true) {
        //$.blockPage();
        $("#login-form").attr("action", "CommonLogin").submit();

        return true;
    }
    return false;
}

$(document).ready(function () {
    unblockPage();
    //Handler
    $("#login-button").click(submitLogin);
    $("#password").keyup(passwordFormat);
    $("#password").keypress(function (e) {
        if (enterKey(e)) {
            submitLogin();
        }
    });
    
    jQuery.validator.addMethod("rutdv", function (value, element) {
        var regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        return this.optional(element) || (regExp.test(jQuery.trim(value)) && validateRutDv("rutdv", "rut"));
    }, "Rut-Dv inválido");
    
    $("#rutdv").keyup(function () {
        var rut = formatear($(this).val(), true);
        $(this).val(rut);
    });

    $("#login-form").validate({
        rules: {
            usuarios: {required: true},
            rutdv: {
                required: true,
                rutdv: true
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 16
            }
        },
        messages: {
            usuarios: {
                required: jQuery.validator.messages.required
            },
            rutdv: {
                required: jQuery.validator.messages.required
            },
            password: {
                required: jQuery.validator.messages.required,
                minlength: jQuery.validator.messages.minlength(6),
                maxlength: jQuery.validator.messages.maxlength(16)
            }
        }
    });



    /*
     if ($("#userType").val() !== '') {
     $("#usuarios option[value='" + $("#userType").val() + "']").prop("selected", true);
     }
     */
});