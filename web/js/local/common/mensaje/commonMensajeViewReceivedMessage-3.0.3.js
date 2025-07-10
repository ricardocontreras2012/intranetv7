function printMessage() {
    $("#view-received-message-form").attr("action", "CommonMensajePrintMessage").attr("target", "_blank").attr("method", "post").submit();
}

function replyMessage() {
    $("#view-received-message-form").attr("action", "CommonMensajeReplyMessage").attr("target", "_self").attr("method", "post").submit();
}

function forwardMessage() {
    const dataString = $("#view-received-message-form").serialize();
    $('#message-iframe', window.parent.document).attr("src", 'CommonMensajeEnableFwd?' + dataString);    
}

function deleteMsg()
{
    $("#confirmacion").modal('show');
}

function removeMsg() {
    $.blockUI();
    $("#confirmacion").modal('hide');
    $("#view-received-message-form").attr("action", "CommonMensajeRemoveReceivedMessages").attr("target", "_self").attr("method", "post").submit();
}

