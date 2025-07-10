const regex = new RegExp("^[a-zA-Z0-9@#\$%&+=_\-]$");

function changePassword() {
    if (validatePwdStrength()) {
        //$.blockPage();
        $("#cambioPassword-form").attr("action", "CommonLoginStackPasswordSave").submit();
    } else
    {
        $("#err-div").html("Contraseña no cumple las características indicadas")
        $("#aviso").modal('show');
    }
}

function validatePwdStrength()
{
    const pwd = $('#passwdNueva').val();
    const strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#\$%&+=_\-])(?=.{10,})");
    return strongRegex.test(pwd);
}

$(document).ready(function () {
    $("#save-button").click(changePassword);

    const regex = /^[a-zA-Z0-9@#$%&*]+$/; // Ejemplo de regex, ajusta según tus reglas

    function validarEntrada(event) {
        const key = event.key;
        if (!regex.test(key)) {
            event.preventDefault();
            return false;
        }
        return true;
    }

    // Aplicar a ambos campos
    $('#passwdNueva, #passwdConfirm').on('keypress', validarEntrada);


    $('#passwdNueva, #passwdConfirm').on('copy paste', function (e) {
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
                minlength: 10,
                maxlength: 16
            },
            passwdConfirm: {
                required: true,
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

    $("#aviso").modal('hide');
    if ($("#err-div").text().trim() !== '') {
        $("#aviso").modal('show');
    }
});
