
function clearProfesorForm() {
    $("#profesor-form").attr("action", 'CommonProfesorSearchEnable?actionCall=CommonProfesorRecordEnable&typeSearch=F&key=' + $("#keyDummy").val());
    $("#profesor-form").attr("target", "_self").submit();
}

function serviceAction(urlVar) {
    $("#search-content-iframe").attr("src", urlVar + '?key=' + $("#keyDummy").val());
}

function serviceActionParm(urlVar) {
    $("#search-content-iframe").attr("src", urlVar + '&key=' + $("#keyDummy").val());
}

function service(urlVar) {
    $("#search-content-iframe").attr("src", urlVar);
}

$(document).ready(function () {
    $("#clear-button").click(clearProfesorForm);
    $("#search-content-div").html("<iframe id=\"search-content-iframe\" name=\"search-content-iframe\" class=\"inner-iframe\"></iframe>");
});