

function changePassword() {
    $("#cambioPassword-form").attr("action", $("#actionDummy").val());
    $("#cambioPassword-form").submit();
}

$(document).ready(function () {


    //Handlers
    $("#save-button").click(changePassword);
    $("#passwdActual").keyup(passwordFormat);
    $("#passwdNueva").keyup(passwordFormat);
    $("#passwdConfirm").keyup(passwordFormat);
    $("#passwdConfirm").keypress(function (e) {
        if (enterKey(e)) {
            changePassword();
        }
    });
    //
    $("#cambioPassword-form").validate({
        event: "blur",
        rules: {
            passwdActual: {
                required: true
            },
            passwdNueva: {
                required: true,
                alphanumeric: true,
                minlength: 10,
                maxlength: 16
            },
            passwdConfirm: {
                required: true,
                alphanumeric: true,
                minlength: 10,
                maxlength: 16,
                equalTo: "#passwdNueva"
            }
        },
        messages: {
            passwdActual: {
                required: jQuery.validator.messages.required
            },
            passwdNueva: {
                required: jQuery.validator.messages.required,
                minlength: jQuery.validator.messages.minlength(10),
                maxlength: jQuery.validator.messages.maxlength(16)
            },
            passwdConfirm: {
                required: jQuery.validator.messages.required,
                minlength: jQuery.validator.messages.minlength(10),
                maxlength: jQuery.validator.messages.maxlength(16),
                equalTo: jQuery.validator.messages.equalTo
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

    if ($("#msg-div").text() !== '') {
        $("#msg-div").dialog("open");
    }

    $("#dialog-form").dialog({
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