
function searchCarrera(posValue) {
    $("#carreras-form").attr("action", "CommonAlumnoGetAluCar?pos=" + posValue + '&key=' + $("#keyDummy").val());
    $("#carreras-form").attr("target", "_self").submit();
}

$(document).ready(function () {
    $("a").click(function () {
        const fieldName = $(this).attr("id");
        searchCarrera(fieldName.substr(fieldName.indexOf("_") + 1));
    });
});