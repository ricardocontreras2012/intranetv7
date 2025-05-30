

function save() {
    if ($("#egresado-form").validate().form() === true) {
        $("#egresado-form").attr("action", "EgresadoMisDatosSave");
        $("#egresado-form").submit();
        return true;
    }
    return false;
}

$(document).ready(function () {

    //Handler
    $("#save-button").click(save);
    //
    $("#egresado-form").validate({
        rules: {
            fechaNac: {required: true, formatoFecha: true},
            region: {required: true},
            comuna: {required: true},
            email: {required: true, multiemail: true},
            emailLaboral: {multiemail: true}
        },
        messages: {
            fechaNac: {required: jQuery.validator.messages.required},
            email: {required: jQuery.validator.messages.required}
        }
    });

    $("#region").change(function () {
        if ($("#region").prop("selectedIndex") > 0){
            $.get('CommonComunaGetComunas', {'region': $(this).val(), 'key': $("#keyDummy").val()}, function (data) {
                $("#comunas").html(data);
            });
        }
    });

    $("#region option[value='" + $("#regionDummy").val() + "']").prop("selected", true);
    $("#comuna option[value='" + $("#comunaDummy").val() + "']").prop("selected", true);

});