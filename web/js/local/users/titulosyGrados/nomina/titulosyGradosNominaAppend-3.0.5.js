
function expedienteAction(action)
{
    $("#tipo").val($("select#tipo-select option").filter(":selected").val());
    $("#nomina-form").attr("action", action);
    $("#nomina-form").attr("method", "POST");
    $("#nomina-form").attr("target", "_self");
    $("#nomina-form").submit();

    return false;
}

function agregarAlumno()
{
    expedienteAction("TitulosyGradosNominaSetNomina");
}

function saveNomina() {
    validNomina();
    if ($("#nomina-form").valid())
    {
        expedienteAction("TitulosyGradosNominaSaveNomina");
    }
}

function validNomina()
{
    $("#nomina-form :input").each(function () {
        var fieldName = $(this).attr("id");

        if (fieldName !== undefined && fieldName !== null)
        {
            if (fieldName.startsWith("ne_")) {
                $(this).rules("add", {
                    number: true, required: true,
                    messages: {number: jQuery.validator.messages.digits, required: jQuery.validator.messages.required}
                });
            }

            if (fieldName.startsWith("fe_")) {
                $(this).rules("add", {
                    required: true,
                    messages: {required: jQuery.validator.messages.required}
                });
            }

            if (fieldName.startsWith("rol_")) {
                $(this).rules("add", {
                    required: true,
                    messages: {required: jQuery.validator.messages.required}
                });
            }
        }
    });
}

$(document).ready(function () {

    $("#save-button").click(saveNomina);

    $("#nomina-form").validate({
        event: "blur",
        rules: {
            "tipo-select": {required: true},
            "agno": {required: true, number: true},
            "nomina": {required: true, number: true}
        },
        messages: {
            "tipo-select": {required: jQuery.validator.messages.required},
            "agno": {required: jQuery.validator.messages.required, number: jQuery.validator.messages.digits},
            "nomina": {required: jQuery.validator.messages.required, number: jQuery.validator.messages.digits}
        }
    });

    $("#nomina-form :input").each(function () {
        var fieldName = $(this).attr("id");

        if (fieldName !== undefined && fieldName !== null)
        {
            if (fieldName.startsWith("fe_"))
            {
                $(this).mask("99/99/9999");
            }
        }
    });
});
