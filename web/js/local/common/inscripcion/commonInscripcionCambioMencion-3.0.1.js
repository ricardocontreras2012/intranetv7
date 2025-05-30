

$(document).ready(function () {

    //Handlers
/*
    $("#confirmacion").dialog({
        autoOpen: true,
        resizable: false,
        modal: true,
        position: "top",
        width: 300,
        buttons: {
            "NO": function () {
                $("cursos-iframe", parent.window.document).html("");
                $(this).dialog("close");
            },
            "SI": function () {
                window.parent.parent.location = "AlumnoInscripcionCambioMencion?key=" + $("#keyDummy").val();
                $(this).dialog("close");
            }
        }
    });
    */

    $("#confirmacion").dialog({
        autoOpen: true,
        resizable: false,
        modal: true,
        position: "top",
        width: 300,
        buttons: {
            "OK": function () {
                $("cursos-iframe", parent.window.document).html("");
                $(this).dialog("close");
            }
        }
    });
});
