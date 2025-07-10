function nextDestinySearch() {
    const dataString = $("#profesor-lista-form").serialize();
    $('#message-iframe', window.parent.document).attr("src", 'CommonMensajeReceiveLista?' + dataString);  
    
    return false;
}

function otroProfesor() {
    const dataString = $("#profesor-lista-form").serialize();
    $('#message-iframe', window.parent.document).attr("src", 'CommonMensajeProfesorSearchEnable?' + dataString);  
    
    return false;
}
