
function inscribir(pos) { 
    $("#pos").val(pos);
    var data_string = $("#cursos-form").serialize();

    $('#inscripcion-iframe', window.parent.document).attr("src", 'CommonInscripcionAddInscripcion?' + data_string);   
}

function showProfesor(profesor, pos, source) {
    var random_number = Math.floor(Math.random() * 10000);
     var html_text = "<div><img width=\"70\" height=\"80\" alt=\" \" src=\"dummy/" + random_number + "/intranetv7/CommonInscripcionGetFotoProfesor?key=" + $("#key").val() + "&pos=" + pos + "&source=" + source + "\"/><p>" + profesor.replace('/0/g', ' ') + "</p></div>";

     parent.$("#profesor-div").html(html_text);
     parent.$("#profesor").modal('show');
     return false;
}

$(document).ready(function () {
});



