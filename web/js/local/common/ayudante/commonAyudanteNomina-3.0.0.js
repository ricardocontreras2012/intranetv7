

function getAyudante(pos, key) {
    loadSearch($("#typeSearchDummy").val(), "CommonAyudanteGetAyudante", pos, key);
}

$(document).ready(function () {

    const key = $("#keyDummy").val();

    //Handler
    $("a").click(function () {
        const field_name = $(this).attr("id");
        getAyudante(field_name.substr(field_name.indexOf("_") + 1), key);
    });

    initTableNomina();
});