
function showSala(modulo, sala, curso, profesor) {
    const moduloAux = '<table class="table table-striped"><tr><th>M\u00f3dulo</td><td>' + modulo + '</td></tr><tr><th>Curso</td><td>' + curso + '</td></tr><tr><th>Profesor</td><td>' + profesor + '</td></tr><tr><th>Sala</td><td>' + sala + '</td></tr></table>';
    $("#sala-div").html(moduloAux);
    $("#sala").modal('show');
    return false;
}

function printHorario() {
    //$("#horario-form").attr("action", "CommonHorarioPrintHorario?key=" + $("#keyDummy").val());
    $("#horario-form").attr("action", "CommonHorarioPrint?key=" + $("#keyDummy").val());
    $("#horario-form").attr("method", "post").attr("target", "_blank").submit();
}

$(document).ready(function () {
    //Handler
    $("#print-button").click(printHorario);
});