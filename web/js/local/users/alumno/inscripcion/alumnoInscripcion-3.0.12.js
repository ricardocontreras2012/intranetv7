
function deleteInscripcion() {
    if (anyChecked("inscripcion-form") === true) {
        parent.$("#confirmacion").modal('show');
    } else {
        parent.$("#aviso").modal('show');
    }
    return false;
}



function printInscripcion() {
    $("#inscripcion-form").attr("action", "CommonAlumnoHorarioInscripcionPrint");
    $("#inscripcion-form").attr("target", "_blank");
    $("#inscripcion-form").submit();
}

function showProfesor(profesor, pos, source) {
    var random_number = Math.floor(Math.random() * 10000);
    var html_text = "<div><img width=\"70\" height=\"80\" alt=\" \" src=\"dummy/" + random_number + "/intranetv7/CommonInscripcionGetFotoProfesor?key=" + $("#key").val() + "&pos=" + pos + "&source=" + source + "\"/><p>" + profesor.replace('/0/g', ' ') + "</p></div>";

    parent.$("#profesor-div").html(html_text);
    parent.$("#profesor").modal('show');
    return false;
}

function getCursosSwap() {
    if (anyChecked("inscripcion-form") === true) {
        parent.$("#title-swap-div").html($("#curso-title").val());
        parent.$('#swap').modal('show');

        var dataString = $("#inscripcion-form").serialize();
                      
        $('#swap-iframe', window.parent.document).attr("src", 'AlumnoInscripcionGetCursosSwap?' + dataString);
       
    } else {
        window.parent.$('#aviso-swap').modal('show');
    }
}

$(document).ready(function () {
});
