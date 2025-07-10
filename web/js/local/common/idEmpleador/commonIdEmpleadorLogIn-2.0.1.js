

function submitLogin() {
    if ($("#usuarios").prop("selectedIndex") >= 1) {
        $("#userType").val($("#usuarios option:selected").val());
        if ($("#login-form").validate().form() === true) {

            $("#login-form").attr("action", "CommonLoginEmpleador");
            $("#login-form").submit();
            return true;
        }
        return false;
    }
    else {
        $("#msg-div").dialog("open");
        return false;
    }
}

$(document).ready(function () {
    //Handlers
    $("#login-button").click(submitLogin);
    $("#password").keyup(passwordFormat);
    $("#password").keypress(function (e) {
        if (enterKey(e)) {
            submitLogin();
        }
    });
    
    $("#rutdv").keyup(function () {
        const rut = formatear($(this).val(), true);
        $(this).val(rut);
    });
    //
    jQuery.validator.addMethod("rutdv", function (value, element) {
        const regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        return this.optional(element) || (regExp.test(value) && validateRutDv("rutdv", "rut"));
    }, "Rut-Dv inválido.");

    $("#login-form").validate({
        rules: {
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
            rutdv: {
                required: jQuery.validator.messages.required
            },
            password: {
                required: jQuery.validator.messages.required,
                minlength: jQuery.validator.messages.minlength(10),
                maxlength: jQuery.validator.messages.maxlength(16)
            }
        }
    });

    $("#msg-div").dialog({
        autoOpen: false,
        resizable: false,
        modal: true,
        width: 300,
        buttons: {
            "OK": function () {
                $(this).dialog("close");
            }
        }
    });

    $("#dialog-form-div").dialog({
        autoOpen: true,
        resizable: false,
        height: 300,
        width: 350,
        modal: true,
        buttons: {
            "Ingresar": function () {
                submitLogin();
            }
        }
    });
});