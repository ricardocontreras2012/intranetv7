function searchCursos(asign) {
    $("#asign").val(asign);
    const data_string = $("#derechos-form").serialize();
    $('#cursos-iframe', window.parent.document).attr("src", 'AlumnoInscripcionGetCursos?' + data_string);
}

$(document).ready(function () {
});



