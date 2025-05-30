
function expedienteAction(action)
{
    $("#tipo").val($("select#tipo-select option").filter(":selected").val());
    $("#nomina-form").attr("action", action);
    $("#nomina-form").attr("method", "POST");
    $("#nomina-form").attr("target", "_self");
    $("#nomina-form").submit();

    return false;
}

function searchNomina()
{
    $("#nomina").val($("#nomina").val().toUpperCase());
    if ($("#nomina-form").valid())
    {
        expedienteAction("TitulosyGradosNominaGetNomina");
    }
}


$(document).ready(function () {
    $("#search-button").click(searchNomina);
    $("#nomina-form").validate({
        event: "blur",
        rules: {
            "tipo-select": {required: true},
            "agno": {required: true, number: true},
            "nomina": {required: true}
        },
        messages: {
            "tipo-select": {required: jQuery.validator.messages.required},
            "agno": {required: jQuery.validator.messages.required, number: jQuery.validator.messages.digits},
            "nomina": {required: jQuery.validator.messages.required}
        }
    });
});
