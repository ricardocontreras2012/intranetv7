

function validaEncuesta() {
    const radios = $("#encuesta-form input[type=radio][name^='P_']");
    const preguntasRevisadas = new Set();

    for (const radio of radios) {
        const name = radio.name;
        if (preguntasRevisadas.has(name)) continue;

        preguntasRevisadas.add(name);
        if (!$(`input[name="${name}"]:checked`).length) {
            const pos = name.lastIndexOf("_");
            const numeroPregunta = name.substring(pos + 1);
            $("#msg-div").html(`Falta responder pregunta ${numeroPregunta}`);
            $("#msg").modal('show');
            return false;
        }
    }

    return true;
}


function saveEncuesta() {
    if (validaEncuesta() === true) {
        $("#encuesta-form").attr("action", "AlumnoEncuestaAyudanteSaveEncuesta");
        $("#encuesta-form").submit();
    }

    return false;
}

$(document).ready(function() {

    //Handlers
    $("#save-button").click(saveEncuesta);
    //
});