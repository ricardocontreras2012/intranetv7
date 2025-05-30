function imprimirCartaPresentacion() {
    $("#solicitud-form").attr("action", "AlumnoPracticaPresentacionPrint");
    $("#solicitud-form").attr("target", "_blank");
    $("#solicitud-form").submit();
}

function imprimirCartaAutorizacion() {
    $("#solicitud-form").attr("action", "AlumnoPracticaAutorizacionPrint");
    $("#solicitud-form").attr("target", "_blank");
    $("#solicitud-form").submit();
}

function deleteAttachSolicitud(key, doc) {
    $("#solicitud-form").attr("action", "AlumnoPracticaDeleteFile?key=" + key + "&doc=" + doc);
    $("#solicitud-form").attr("target", "_self");
    $("#solicitud-form").submit();
}

function uploadDoc() {
    $("#solicitud-form").attr("action", "AlumnoPracticaUploadForm");
    $("#solicitud-form").attr("target", "_self");
    $("#solicitud-form").submit();
}

$(document).ready(function () {
    //Handlers
    $("#upload-button").click(uploadDoc);
    $("#print-presentacion-button").click(imprimirCartaPresentacion);
    $("#print-autorizacion-button").click(imprimirCartaAutorizacion);

    jQuery("textarea").each(function () {
        $(this).val(jQuery.trim($(this).val()));
    });
});