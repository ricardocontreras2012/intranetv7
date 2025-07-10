
$(document).ready(function () {
    //Handlers
    $("a").click(function () {
        const field = $(this).attr("id");
        getMaterialesAlumno(field.substr(field.indexOf("_") + 1));
    });
});


