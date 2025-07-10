
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

function validForm() {
    const $respuesta = $("#respuesta");
    const resolucion = $("#resolucion").val();
    const respuesta = $respuesta.val().trim();
    const tipo = $("#tipo").val();

    if (!resolucion) {
        alert('Falta ingresar resolución');
        return false;
    }

    if (!respuesta) {
        if (resolucion === "A" && tipo !== "40") {
            $respuesta.val("Aprobada");
        } else if (resolucion === "TR") {
            $respuesta.val("En Trámite");
        } else if (tipo === "40") {
            alert('Falta ingresar fecha plazo de matrícula');
            return false;
        } else {
            alert('Falta ingresar respuesta');
            return false;
        }
    }

    return true;
}


$(document).ready(function () {
    $("#save-button").click(grabarResolucion);
});
