

function emitir()
{
    $("#acta-form").attr("action", "ProfesorActaEmitirActaConcepto");
    $("#acta-form").attr("onsubmit", "return true;");
    $("#acta-form").submit();
}

$(document).ready(function () {


    //Handlers
    $("#emitir-button").click(emitirActa);
    $("#print-button").click(printActa);
    $("#export-button").click(exportActa);
    //
    $("#acta-form").validate({
        validationEventTriggers: "blur",
        success: function (label) {
            const id = label.attr("for");
            const input = $("#" + id);
            const value = input.val().toUpperCase();

            ((value === "AD") || (value === "A") || (value === "B")) ? input.attr("class", "aprobado") : input.attr("class", "reprobado");
        },
        event: "blur",
        rules: {},
        messages: {}
    });

});

function emitirActa() {
    $("#confirmacion").modal('show');
    return false;
}

function exportActa() {
    $("#format").val("XLS");
    $("#acta-form").attr("action", "ProfesorActaExportActa");
    $("#acta-form").submit();
}

function printActa() {
    const contentDisposition = $("#contentDisposition").val();
    $("#contentDisposition").val("");
    $("#format").val("PDF");
    $("#acta-form").attr("action", "ProfesorActaExportActa");
    $("#acta-form").attr("target", "_blank");
    $("#acta-form").submit();
    $("#contentDisposition").val(contentDisposition);
}