function searchCalificaciones() {
    if ($("#agno-form").validate().form() === true) {
        $("#calificaciones-content-iframe").attr("src", 'AsistenteGetCalificaciones?' + $("#agno-form").serialize());
        //$("#calificaciones-content-iframe").attr("src", "AsistenteGetCalificaciones?key="+$("#key").val());
    }

}

$(document).ready(function () {
    //Handlers
    $("#search-button").click(searchCalificaciones);

    $("#agno-form").validate({
        rules: {
            agno: {
                required: true,
                min: 1900
            }
        }
    });
});