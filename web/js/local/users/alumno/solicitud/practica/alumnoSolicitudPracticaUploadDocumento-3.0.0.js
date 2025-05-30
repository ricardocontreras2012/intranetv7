function upload() {
    $("#solicitud-form :input").each(function () {
        var fieldName = $(this).attr("id");
        if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("upload")) {
            $(this).removeAttr('id');
        }
    });
    //$.blockPage();
    $("#solicitud-form").attr("action", "AlumnoPracticaAddAttachFile");
    $("#solicitud-form").submit();
}

$(document).ready(function () {
    //Handlers
    $("#upload-button").click(upload);
});

