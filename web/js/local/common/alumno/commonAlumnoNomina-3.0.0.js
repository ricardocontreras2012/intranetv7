

function getAlumno(pos, key) {
    loadSearch($("#typeSearchDummy").val(), "CommonAlumnoGetAlumno", pos, key);
}

$(document).ready(function () {
    const key = $("#keyDummy").val();
    //Handler
    $("a").click(function () {
        const field_name = $(this).attr("id");
        getAlumno(field_name.substr(field_name.indexOf("_") + 1), key);
    });

    initTableNomina();
});