function showAvisoDestinatario() {
    $("#destinatarios").modal('show');
}

function showAvisoBarra() {
    $("#barra").modal('show');
}

function checkedBar() {
    return ($("#destinatarios-form input[type=checkbox][name^=barraCk_]:checked").length > 0);
}

function checkedBody() {
    return ($("#destinatarios-form input[type=checkbox][name^=ck_]:checked").length > 0);
}

function nextDestiny() {

    const listaBody = $("#largoLista").val();
    const listaBarra = $("#largoBarra").val();
    if (listaBarra > 0) {
        if (!checkedBar()) {
            showAvisoBarra();
            return false;
        }
    }
    if (listaBody > 0) {
        if (!checkedBody()) {
            showAvisoDestinatario();
            return false;
        }
    }

    const dataString = $("#destinatarios-form").serialize();
    $('#message-iframe', window.parent.document).attr("src", 'CommonMensajeReceiveLista?' + dataString);    

    //blockPage();
    return false;
}

