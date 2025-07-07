function validarEncuesta() {
    var errores = [];

    // Preguntas de la Sección I (P_1_nn) con su texto para mensajes
    var preguntasSeccion1 = [
        {name: "P_1_1", texto: "¿Cuál es su género?"},
        {name: "P_1_2", texto: "¿A qué porcentaje de las clases de esta asignatura asistió Ud. durante el semestre?"},
        {name: "P_1_3", texto: "¿Con qué frecuencia cumplió Ud. con las actividades académicas y obligaciones de esta asignatura?"},
        {name: "P_1_4", texto: "Sin considerar las horas de clases, ¿cuántas horas a la semana, aproximadamente, dedicó Ud. a la preparación y estudio de esta asignatura?"}
    ];

    // Validar Sección I
    for (var i = 0; i < preguntasSeccion1.length; i++) {
        if (!$('input[name="' + preguntasSeccion1[i].name + '"]:checked').val()) {
            errores.push("Sección I: " + preguntasSeccion1[i].texto);
        }
    }

    // Validar Sección II (P_2_nn)
    // Obtenemos todos los grupos P_2_nn dinámicamente
    var nombresP2 = [];
    $('input[name^="P_2_"]').each(function() {
        var name = $(this).attr('name');
        if ($.inArray(name, nombresP2) === -1) {
            nombresP2.push(name);
        }
    });

    for (var j = 0; j < nombresP2.length; j++) {
        if ($('input[name="' + nombresP2[j] + '"]:checked').length === 0) {
            // Obtener texto de la pregunta desde la tabla
            var textoPregunta = $('input[name="' + nombresP2[j] + '"]').closest('tr').find('td:first').text().trim();
            errores.push("Sección II: " + textoPregunta);
        }
    }

    if (errores.length > 0) {
        alert("Debe responder todas las preguntas obligatorias:\n\n" + errores.join("\n"));
        return false;
    }

    return true;
}

function saveEncuesta() {
    if (validarEncuesta() === true) {
        $("#encuesta-form").attr("action", "AlumnoEncuestaDocenteSaveEncuesta");
        $("#encuesta-form").submit();
    }

    return 0;
}

$(document).ready(function () {
    //Handlers
    //$("#save-button").click(saveEncuesta);
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
});