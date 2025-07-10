

function downLoadDocumento(docto) {
    $("#documento").val(docto);
    $("#solicitud-form").attr("action", "CommonSolicitudDownLoadDocumento");
    $("#solicitud-form").attr("target", "_self");
    $("#solicitud-form").submit();
}

$(document).ready(function () {

    //Handlers
    $("a").click(function () {
        const field_name = $(this).attr("id");
        downLoadDocumento(field_name.substr(field_name.indexOf("_") + 1));
    });
});