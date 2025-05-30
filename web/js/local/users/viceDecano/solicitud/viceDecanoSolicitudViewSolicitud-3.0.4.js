
function grabarResolucion() {
    if ($("#solicitud-form").validate().form() === true && validForm() === true) {
        $("#solicitud-form").attr("action", "ViceDecanoSolicitudSaveResolucion");
        $("#solicitud-form").attr("target", "_self");
        $("#solicitud-form").submit();
        return true;
    } else
    {
        return false;
    }
}

function validForm()
{
    const resolucion = $("#resolucion").val();
    const respuesta = $("#respuesta").val().trim();
    const tipo = $("#tipo").val();

    if (!resolucion) {
        return alert('Falta ingresar resolución'), false;
    }

    if (!respuesta) {
        if (resolucion === "A" && tipo !== "40") {
            $("#respuesta").val("Aprobada");
        } else if (resolucion === "TR") {
            $("#respuesta").val("En Trámite");
        } else if (tipo === "40") {
            return alert('Falta ingresar fecha plazo de matrícula'), false;
        } else {
            return alert('Falta ingresar respuesta'), false;
        }
    }

    return true;
}

$(document).ready(function () {
    $("#save-button").click(grabarResolucion);
});
