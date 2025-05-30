
function verificarCertificado() {
    $("#verificacion-form").attr("action", "VerificacionCertificado");
    $("#verificacion-form").submit();
}

function clearForm() {
    $("#errorBox").hide('slow');
}

$(document).ready(function () {

    //Handlers
    $("#verificar-button").click(verificarCertificado);
    $("#clear-button").click(clearForm);
    //
    $("#verificacion").validate({
        rules: {
            folio: {
                required: true,
                digits: true
            },
            verificador: {
                required: true,
                alphanumeric: true,
                minlength: 16,
                maxlength: 16
            }
        },
        messages: {
            folio: {
                required: jQuery.validator.messages.required,
                digits: jQuery.validator.messages.digits
            },
            verificador: {
                required: jQuery.validator.messages.required,
                minlength: jQuery.validator.messages.minlength(16),
                maxlength: jQuery.validator.messages.maxlength(16)
            }
        }
    });
});