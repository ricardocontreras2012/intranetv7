

function validaEncuesta() {

    var seleccionado = true, pregunta;

    $("#encuesta-form input[type=radio]").each(function() {
        if ($(this).attr("name").startsWith("P_")) {
            pregunta = $(this).attr("name");
            if ($("input:radio[name=" + pregunta + "]:checked").length === 0) {
                seleccionado = false;
                return false;
            }
        }
    });

    if (!seleccionado) {
        const pos = pregunta.lastIndexOf("_");
        const apartado = pregunta.substring(2, pos);
        var apartadoMsg;
        if (apartado === "1") {
            apartadoMsg = "AUTOEVALUACIÓN DEL ESTUDIANTE";
        } else {
            apartadoMsg = "EVALUACIÓN AL DOCENTE";
        }
        $("#msg-div").html('Falta responder pregunta ' + pregunta.substring(pos + 1) + " del apartado " + apartadoMsg);
        $("#msg").modal('show');
        return false;
    }
    return true;
}


function saveEncuesta() {
    if (validaEncuesta() === true) {
        $("#encuesta-form").attr("action", "AlumnoEncuestaDocenteSaveEncuesta");
        $("#encuesta-form").submit();
    }

    return false;
}

$(document).ready(function () {

    //Handlers
    $("#save-button").click(saveEncuesta);
    $("#encuesta-form").validate({
        event: "blur",
        rules: {
            comentarioPositivo: {
                maxlength: 500
            },
            comentarioMejora: {
                maxlength: 500
            }
        },
        messages: {
            comentarioPositivo: {
                maxlength: jQuery.validator.messages.maxlength(500)
            },
            comentarioMejora: {
                maxlength: jQuery.validator.messages.maxlength(500)
            }
        }
    });
    //
});