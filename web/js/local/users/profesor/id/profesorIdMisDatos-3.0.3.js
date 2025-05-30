
function save() {
    if ($("#profesor-form").validate().form()) {
        $("#profesor-form").attr("action", "ProfesorMisDatosSave");
        $("#profesor-form").submit();
        return true;
    }
    return false;
}

function formatPhoneNumber(input) {
    // Obtener el valor actual del campo
    let value = input.value;

    // Eliminar todo lo que no sea un número
    value = value.replace(/\D/g, '');

    // Limitar a 8 caracteres numéricos
    if (value.length > 8) {
        value = value.slice(0, 8);
    }

    // Establecer el valor en el campo de entrada
    input.value = value;
}

$(document).ready(function () {
    //Handler
    $("#save-button").click(save);
    $("#info-modal").modal('show');
    //

    $("#profesor-form").validate({
        rules: {
            /*region: {required: true},
            comuna: {required: true},
            fechaNac: {
                required: true,
                formatoFecha: true
            },*/
            email: {
                required: true,
                multiemail: true
            }
        },
        messages: {
            /*region: {required: jQuery.validator.messages.required},
            comuna: {required: jQuery.validator.messages.required},
            fechaNac: {
                required: jQuery.validator.messages.required
            },*/
            email: {
                required: jQuery.validator.messages.required}
            
        }
    });

    $("#region").change(function () {
        $.get('CommonComunaGetComunas', {
            'region': $(this).val(),
            'key': $("#keyDummy").val()
        }, function (data) {
            $("#comunas").html(data);
        });
    });

    $("#region option[value='" + $("#regionDummy").val() + "']").prop("selected", true);
    $("#comuna option[value='" + $("#comunaDummy").val() + "']").prop("selected", true);
});