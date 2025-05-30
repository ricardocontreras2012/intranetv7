function printMessage() {
    $("#view-sent-message-form").attr("action", "CommonMensajePrintMessage");
    $("#view-sent-message-form").attr("target", "_blank").attr("method", "post").submit();
}

function deleteMsg()
{
    $("#confirmacion").modal('show');
}

function removeMsg() {
    $.blockUI();
    $("#confirmacion").modal('hide');
    $("#view-sent-message-form").attr("action", "CommonMensajeRemoveSentMessages");
    $("#view-sent-message-form").attr("target", "_self").attr("method", "post").submit();
}


