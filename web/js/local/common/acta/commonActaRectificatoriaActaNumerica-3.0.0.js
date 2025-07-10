
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
    const contentDisposition = $("#contentDisposition").val();
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
            const fields = $(this).parents('form:eq(0),body').find('button,input,textarea,select');
            const index = fields.index(this);
            if (index > -1 && (index + 1) < fields.length) {
                fields.eq(index + 1).focus();
            }
        }
    });

    //
    $("#acta-form").validate({
        validationEventTriggers: "blur",
        event: "blur",
        rules: {},
        messages: {},
        success: function (label) {
            const inputId = label.attr("for");
            const $input = $("#" + inputId);
            const value = parseFloat($input.val().replace(",", "."));

            const newClass = value < 4 ? "reprobado" : "aprobado";
            $input.attr("class", `form-control ${newClass}`);
        }
    });


    $("#acta-form :input").each(function () {
        const field_name = $(this).attr("id");
        if (field_name.startsWith("nota_")) {
            $(this).rules("add", {
                formatoNota: true
            });
        }
    });
});