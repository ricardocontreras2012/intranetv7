

function getAlumno(pos, key) {
    loadSearch($("#typeSearchDummy").val(), "CommonAlumnoGetAlumno", pos, key);
}

$(document).ready(function () {
    var key = $("#keyDummy").val();
    //Handler
    $("a").click(function () {
        var field_name = $(this).attr("id");
        getAlumno(field_name.substr(field_name.indexOf("_") + 1), key);
    });

    initTableNomina();
});