
function deleteInscripcion() {
    if (anyChecked("inscripcion-form") === true) {
        parent.$("#confirmacion-elimina").modal('show');
    } else {
        parent.$("#aviso-elimina").modal('show');
    }
    return false;
}

function printInscripcion() {
    $("#inscripcion-form").attr("action", "CommonAlumnoHorarioInscripcionPrint");
    $("#inscripcion-form").attr("target", "_blank");
    $("#inscripcion-form").submit();
}

function showProfesor(profesor, pos, source) {
    const random_number = Math.floor(Math.random() * 10000);
    const html_text = "<div><img width=\"70\" height=\"80\" alt=\" \" src=\"dummy/" + random_number + "/intranetv7/CommonInscripcionGetFotoProfesor?key=" + $("#key").val() + "&pos=" + pos + "&source=" + source + "\"/><p>" + profesor.replace('/0/g', ' ') + "</p></div>";

    parent.$("#profesor-div").html(html_text);
    parent.$("#profesor").modal('show');
    return false;
}

function changeForce(pos, force)
{
    $("#pos").val(pos);
    $("#force").val(force==="F"?"N":"F");
    $("#inscripcion-form").attr("action", "CommonInscripcionSaveForce");
    $("#inscripcion-form").attr("target", "_self");
    $("#inscripcion-form").submit();
}

$(document).ready(function () {
});
