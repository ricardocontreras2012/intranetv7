function nueva()
{
    $("#solicitudes-form").attr("action", "SecretariaDocenteConvalidacionNewSolicitud");
    $("#solicitudes-form").attr("target", "_self");
    $("#solicitudes-form").submit();
}

function eliminar()
{
    $("#solicitudes-form").attr("action", "SecretariaDocenteConvalidacionDeleteSolicitud");
    $("#solicitudes-form").attr("target", "_self");
    $("#solicitudes-form").submit();
}

function getSolicitud(pos)
{
    $("#pos").val(pos);
    $("#solicitudes-form").attr("action", "SecretariaDocenteConvalidacionGetSolicitud");
    $("#solicitudes-form").attr("target", "_self");
    $("#solicitudes-form").submit();
}

$(document).ready(function () {
    $("#add-button").click(nueva);
    $("#delete-button").click(eliminar);

    $("a").click(function () {
        const field_name = $(this).attr("id");
        getSolicitud(field_name.substr(field_name.indexOf("_") + 1));
    });
});
