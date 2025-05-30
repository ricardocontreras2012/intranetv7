

function save() {
    if ($("#alumno-form").validate().form() === true) {
        $("#alumno-form").attr("action", "AlumnoMisDatosSave");
        $("#alumno-form").submit();
        return true;
    }
    return false;
}

$(document).ready(function() {

    $("#save-button").click(save);
    //
    $("#alumno-form").validate({
        rules: {
            region:{required: true},
            comuna:{required: true},
            fechaNac: {required: true, formatoFecha: true},
            email: {required: true, multiemail: true},
            emailLaboral: {multiemail: true}
        },
        messages: {
            region:{required: jQuery.validator.messages.required},
            comuna:{required: jQuery.validator.messages.required},
            fechaNac: {required: jQuery.validator.messages.required},
            email: {required: jQuery.validator.messages.required}
        }
    });

    $("#region").change(function() {
        $.get('CommonComunaGetComunas', {'region': $(this).val(), 'key': $("#keyDummy").val()}, function(data) {
            $("#comunas").html(data);
        });
    });

    $("#region option[value='" + $("#regionDummy").val() + "']").prop("selected", true);
    $("#comuna option[value='" + $("#comunaDummy").val() + "']").prop("selected", true);
});