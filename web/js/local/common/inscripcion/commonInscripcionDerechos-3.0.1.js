function searchCursos(pos) {
    $("#pos").val(pos);
    var data_string = $("#derechos-form").serialize();

    $('#cursos-iframe', window.parent.document).attr("src", 'CommonInscripcionGetCursos?' + data_string);
}

$(document).ready(function () {
});



