
function clearAlumnoForm() {
    $("#alumno-form").attr("action", 'CommonAlumnoSearchEnable?actionCall=CommonAlumnoRecordEnable&actionNested=CommonAlumnoGetRecord&typeSearch=F&key=' + $("#keyDummy").val());
    $("#alumno-form").attr("target", "_self").submit();
}

function service(urlVar, printAction) {
    $("#printAction").val(printAction);
    $("#search-content-iframe").attr("src", urlVar);
}

function serviceAction(urlVar, printAction) {
    $("#printAction").val(printAction);
    $("#search-content-iframe").attr("src", urlVar + '?key=' + $("#keyDummy").val());
}

function serviceActionBlank(urlVar) {
    $("#alumno-form").attr("action", urlVar + '?key=' + $("#keyDummy").val());
    $("#alumno-form").attr("target", "_blank").submit();
}

function serviceActionBlankParm(urlVar) {
    $("#alumno-form").attr("action", urlVar + '&key=' + $("#keyDummy").val());
    $("#alumno-form").attr("target", "_blank").submit();
}

function print() {
    $("#alumno-form").attr("action", $("#printAction").val() + "?key=" + $("#keyDummy").val());
    $("#alumno-form").attr("target", "_blank").submit();
}

$(document).ready(function () {
    //Handlers
    $("#print-button").click(print);
    $("#clear-button").click(clearAlumnoForm);
    //  
    $("#search-content-div").html("<iframe id=\"search-content-iframe\" name=\"search-content-iframe\" class=\"inner-iframe\"></iframe>");
});