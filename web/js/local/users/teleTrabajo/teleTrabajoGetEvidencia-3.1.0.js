function downloadFileToRow(fila)
{
    $("#pos").val(fila);
    $("#evidencia-form").attr("action", "TeleTrabajoDownLoadEvidencia");
    $("#evidencia-form").submit();
}

$(document).ready(function () {
    $("#posTarea").val($("#pos").val());  
});