function nextDestinySearch() {
    const dataString = $("#ayudante-lista-form").serialize();
    $('#message-iframe', window.parent.document).attr("src", 'CommonMensajeReceiveLista?' + dataString);  
    
    return false;
}

function otroAyudante() {
    const dataString = $("#ayudane-lista-form").serialize();
    $('#message-iframe', window.parent.document).attr("src", 'CommonMensajeAyudanteSearchEnable?' + dataString);  
    
    return false;
}
