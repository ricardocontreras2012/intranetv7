

function clearAyudanteForm() {
    $("#ayudante-form").attr("action", 'CommonAyudanteSearchEnable?actionCall=CommonAyudanteRecordEnable&typeSearch=F&key=' + $("#keyDummy").val());
    $("#ayudante-form").attr("target", "_self").submit();
}

function service(urlVar) {
    $("#search-content-iframe").attr("src", urlVar);
}

function serviceAction(urlVar) {
    $("#search-content-iframe").attr("src", urlVar + '?key=' + $("#keyDummy").val());
}

function serviceActionBlank(urlVar) {
    $("#ayudante-form").attr("action", urlVar + '?key=' + $("#keyDummy").val());
    $("#ayudante-form").attr("target", "_blank").submit();
}

function serviceActionBlankParm(urlVar) {
    $("#ayudante-form").attr("action", urlVar + '&key=' + $("#keyDummy").val());
    $("#ayudante-form").attr("target", "_blank").submit();
}


function serviceActionPrint(urlVar, printAction) {
    $("#printAction").val(printAction);
    $("#search-content-iframe").attr("src", urlVar + '?key=' + $("#keyDummy").val());
}

$(document).ready(function () {
    $("#clear-button").click(clearAyudanteForm);
    $("#search-content-div").html("<iframe id=\"search-content-iframe\" name=\"search-content-iframe\" class=\"inner-iframe\"></iframe>");
});