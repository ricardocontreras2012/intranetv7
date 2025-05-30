
function getProfesor(pos, key) {
    loadSearch($("#typeSearchDummy").val(), "CommonProfesorGetProfesor", pos, key);
}

$(document).ready(function () {

    var key = $("#keyDummy").val();

    //Handler
    $("a").click(function () {
        var field_name = $(this).attr("id");
        getProfesor(field_name.substr(field_name.indexOf("_") + 1), key);
    });

    initTableNomina();
});