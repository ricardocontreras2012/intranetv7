

function sortear()
{
    $("#confirmacion").modal("hide");
    $("#confirmacion-form").attr("action", "AlumnoExamenAPGenerar");
    $("#confirmacion-form").attr("target", "_self");
    $("#confirmacion-form").submit();

    return true;
}

function cerrar()
{
    $("#confirmacion").modal("hide");
}

$(document).ready(function () {
    $("#confirmacion").modal("show");
    //Handlers
});
