function uploadListaRUN()
{       
    $("#lista-rut-form").attr("action", "CommonMensajeUploadListaRUN").attr("method", "post").attr("target", "_self").submit();    
}

$(document).ready(function () {
    $("#upload-button").click(uploadListaRUN);
    //unblockPage();
});