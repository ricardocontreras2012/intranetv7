
function emitir()
{
    $("#acta-form").attr("action", "ProfesorActaEmitirActaNumerica");
    $("#acta-form").attr("onsubmit", "return true;");
    $("#acta-form").submit();
}

function emitirActa() {
    if ($("#acta-form").valid()) {
        if (hayNota())
        {
            $("#confirmacion").modal('show');
        } else
        {
            $("#warning").modal('show');
        }
    }
    return false;
}

function hayNota()
{
    let flag = false;

    $("#acta-form :input").each(function () {
        const field_name = $(this).attr("id");
        if (field_name.startsWith("nota_")) {
            if ($(this).val() !== "")
            {
                flag = true;
            }
        }
    });

    return flag;
}

function habilita() {
    return $("#puedeEmitirDummy").val() !== false;
}

function exportActa() {
    $("#acta-form").attr("action", "CommonActaExport");
    $("#acta-form").submit();
}

function printActa() {
    $("#format").val("PDF");
    $("#acta-form").attr("action", "CommonActaPrint");
    $("#acta-form").attr("target", "_blank");
    $("#acta-form").submit();
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
        success: function (label) {
            const id = label.attr("for");
            const input = $("#" + id);
            const value = input.val();

            (value < 4) ? input.attr("class", "form-control no-padding col-lg-6 col-sm-12 reprobado") : input.attr("class", "form-control no-padding col-lg-6 col-sm-12 aprobado");
        },
        event: "blur",
        rules: {},
        messages: {}
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