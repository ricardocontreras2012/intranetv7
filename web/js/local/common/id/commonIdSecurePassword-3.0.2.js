const regex = new RegExp("^[a-zA-Z0-9@#\$%&+=_\-]$");

function changePassword() {
    if ($("#cambioPassword-form").validate().form() === true)
    {
        if (validatePwdStrength()) {
            //$.blockPage();
            $("#cambioPassword-form").attr("action", "CommonLoginStackPasswordSave").submit();
        } else
        {
            $("#err-div").html("Contraseña no cumple las características indicadas")
            $("#aviso").modal('show');
        }
    }
}

function validatePwdStrength()
{
    const pwd = $('#passwdNueva').val();
    const strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#\$%&+=_\-])(?=.{8,})");
    return strongRegex.test(pwd);
}

$(document).ready(function () {
    $("#save-button").click(changePassword);

    $('#passwdNueva').bind("keypress", function (event) {
        const key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
        if (!regex.test(key)) {
            event.preventDefault();
            return false;
        }
    });

    $('#passwdConfirm').bind("keypress", function (event) {
        const key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
        if (!regex.test(key)) {
            event.preventDefault();
            return false;
        }
    });

    $('#passwdConfirm').bind('copy paste', function (e) {
        e.preventDefault();
    });

    $("#cambioPassword-form").validate({
        event: "blur",
        rules: {
            passwdActual: {
                required: true
            },
            passwdNueva: {
                required: true,
                minlength: 8,
                maxlength: 16
            },
            passwdConfirm: {
                required: true,
                minlength: 8,
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
                minlength: jQuery.validator.messages.minlength(8),
                maxlength: jQuery.validator.messages.maxlength(16)
            },
            passwdConfirm: {
                required: jQuery.validator.messages.required,
                minlength: jQuery.validator.messages.minlength(8),
                maxlength: jQuery.validator.messages.maxlength(16),
                equalTo: jQuery.validator.messages.equalTo
            }
        }
    });

    $("#aviso").modal('hide');
    if ($("#err-div").text().trim() !== '') {
        $("#aviso").modal('show');
    }
});

