function showModal()
{
   $("#inscripcion").modal('show'); 
}

function getMalla() {
    $("#alumno-form").attr("action", "CommonAlumnoGetMalla?key=" + $("#keyDummy").val());
    $("#alumno-form").attr("target", "_blank");
    $("#alumno-form").submit();
}

function showCalendario()
{
    const url = $("#urlCalendario").val();
    window.open(url, "Calendario", "width=700,height=550,scrollbars=yes");
}

function showNormativa()
{
    const url = $("#urlNormativa").val();
    window.open(url, "Normativa", "width=700,height=550,scrollbars=yes");
}

function recargaListaCursos()
{
    const dataString = {'key': $("#keyDummy").val()};

    jQuery.ajax({
        url: "AlumnoMisCursos",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#div-mis-cursos").html(data);
        },
        async: false
    });
}

$(document).ready(function () {
    
    showAlertMessages();
    loadIframeCentral();
});