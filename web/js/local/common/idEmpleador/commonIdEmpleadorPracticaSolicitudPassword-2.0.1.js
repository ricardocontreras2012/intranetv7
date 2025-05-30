

function submitLogin() {
    if ($("#usuarios").prop("selectedIndex") >= 1) {
        $("#userType").val($("#usuarios option:selected").val());
        if ($("#login-form").validate().form() === true) {
            $("#login-form").attr("action", "CommonPasswordAskNewPassword");
            $("#login-form").submit();

        }
        return false;
    }
    else {
        $("#msg-div").dialog("open");
        return false;
    }
}

$(document).ready(function () {
    //Handler
    $("#enter-button").click(submitLogin);
    $("#rutdv").keypress(function (e) {
        if (enterKey(e)) {
            submitLogin();
        }
    });
    
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
            rutdv: {
                required: true,
                rutdv: true
            }
        },
        messages: {
            rutdv: {
                required: jQuery.validator.messages.required
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
        height: 225,
        width: 350,
        modal: true,
        buttons: {
            "Solicitar": function () {
                submitLogin();
            }
        }
    });
});