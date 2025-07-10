
function newMessage() {
    $("#message-iframe").attr("src", 'CommonMensajeEnableListaDestinatarios?key=' + $("#key").val());
}

function getReceivedMessages() {
    $.blockUI();
    $("#message-iframe").attr("src", 'CommonMensajeGetReceivedMessages?key=' + $("#key").val());
    $("#tipo").val("R");
}

function getSentMessages() { 
    $.blockUI();
    $("#message-iframe").attr("src", 'CommonMensajeGetSentMessages?key=' + $("#key").val());
    $("#tipo").val("S");
}

function checked() {        
    
    if ($("#message-iframe").contents().find("#tipo").val() === "R")
    {
        return $("#message-iframe").contents().find("#received-messages-form input[type=checkbox]:checked").length > 0;
    } else
    {
        return $("#message-iframe").contents().find("#sent-messages-form input[type=checkbox]:checked").length > 0;
    }
}

function deleteMessage() {
    if (checked() === true) {
        $("#confirmacion").modal('show');
    } else {
        showAviso();
    }
    return false;
}


function removeMsgs() {
    $.blockUI();
    let form = $("#message-iframe").contents().find("#received-messages-form");
    let actionName = "CommonMensajeRemoveReceivedMessages";

    if ($("#message-iframe").contents().find("#tipo").val() === "S") {
        actionName = "CommonMensajeRemoveSentMessages";
        form = $("#message-iframe").contents().find("#sent-messages-form");
    }

    $("#confirmacion").modal('hide');
    form.attr("action", actionName).attr("target", "_self").attr("method", "post").submit();
}

function showAviso() {
    $("#aviso").modal('show');
}

$(document).ready(function () {
    $("#confirmacion").modal('hide');
    $("#aviso").modal('hide');
    $("#destinatarios").modal('hide');
    $("#barra").modal('hide');

    getReceivedMessages();
});