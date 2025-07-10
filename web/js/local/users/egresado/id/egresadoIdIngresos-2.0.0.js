

function searchCarrera(posValue) {
    $("#carreras-form").attr("action", "EgresadoLoginSeleccionarIngreso?pos=" + posValue + '&key=' + $("#keyDummy").val());
    $("#carreras-form").attr("target", "_self");
    $("#carreras-form").submit();
}

$(document).ready(function () {

    //Handler
    $("a").click(function () {
        const field_name = $(this).attr("id");
        searchCarrera(field_name.substr(field_name.indexOf("_") + 1));
    });
});