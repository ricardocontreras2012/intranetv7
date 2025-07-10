

function validaEncuesta() {

    let seleccionado = true, pregunta;

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

        $("#msg-div").html('Falta responder pregunta ' + pregunta.substring(pos + 1));
        $("#msg").modal('show');
        return false;
    }
    return true;
}

function saveEncuesta() {    
    if (validaEncuesta() === true) {
        $("#encuesta-form").attr("action", "AlumnoEncuestaDocenteSaveEncuesta5taSemana");
        $("#encuesta-form").submit();
    }

    return false;
}

$(document).ready(function() {
    //Handlers
    $("#save-button").click(saveEncuesta);
    if($("#bienvenidaDummy").val() === "true"){
        $("#msg-bienvenida").modal('show');
    }
});