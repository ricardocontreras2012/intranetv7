

function emitirActa() {
    if ($("#acta-form").valid()) {
        $("#confirmacion").modal('show');
    }
    return false;
}

function emite()
{
    $("#acta-form").attr("action", "CommonActaRectificatoriaEmitirActa");
    $("#acta-form").attr("onsubmit", "return true;");
    $("#acta-form").submit();
}

function habilita() {
    return $("#puedeEmitirDummy").val() !== false;
}

function exportActa() {
    $("#format").val("XLS");
    $("#acta-form").attr("action", "ProfesorActaExportActa");
    $("#acta-form").submit();
}

function printActa() {
    var contentDisposition = $("#contentDisposition").val();
    $("#contentDisposition").val("");
    $("#format").val("PDF");
    $("#acta-form").attr("action", "ProfesorActaExportActa");
    $("#acta-form").attr("target", "_blank");
    $("#acta-form").submit();
    $("#contentDisposition").val(contentDisposition);
}

$(document).ready(function () {

    //Handlers
    $("#emitir-button").click(emitirActa);
    $("#print-button").click(printActa);
    $("#export-button").click(exportActa);

    $("#acta-form :input").keydown(function (e) {
        if (enterKey(e)) {
            $("#acta-form").valid();
            var fields = $(this).parents('form:eq(0),body').find('button,input,textarea,select');
            var index = fields.index(this);
            if (index > -1 && (index + 1) < fields.length) {
                fields.eq(index + 1).focus();
            }
        }
    });

    //
    $("#acta-form").validate({
        validationEventTriggers: "blur",
        success: function (label) {
            var id = label.attr("for");
            var input = $("#" + id);
            var value = input.val();

            (value < 4) ? input.attr("class", "form-control reprobado") : input.attr("class", "form-control aprobado");
        },
        event: "blur",
        rules: {},
        messages: {}
    });

    $("#acta-form :input").each(function () {
        var field_name = $(this).attr("id");
        if (field_name.startsWith("nota_")) {
            $(this).rules("add", {
                formatoNota: true
            });
        }
    });
});