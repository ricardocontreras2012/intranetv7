function nextDestinySearch() {
    const dataString = $("#alumno-lista-form").serialize();
    $('#message-iframe', window.parent.document).attr("src", 'CommonMensajeReceiveLista?' + dataString);  
    
    return false;
}

function otroAlumno() {
    const dataString = $("#alumno-lista-form").serialize();
    $('#message-iframe', window.parent.document).attr("src", 'CommonMensajeAlumnoSearchEnable?' + dataString);  
    
    return false;
}
