
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
    $("#nomina").val($("#nomina").val().toUpperCase());
    expedienteAction("TitulosyGradosNominaSetNomina");
}

$(document).ready(function () {
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

    /*$('#datetimepicker1').datetimepicker({
     format: 'DD/MM/YYYY',
     locale: 'es'
     });*/

    $('#fechaNomina').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd/mm/yyyy',
        weekStartDay: 1
    });
});
