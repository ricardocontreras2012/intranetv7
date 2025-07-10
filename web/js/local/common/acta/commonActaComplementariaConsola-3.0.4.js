
function getCursos()
{
    $("#cursos-form").attr("action", "CommonActaComplementariaGetCursos").attr("target", "_self").submit();
}

function emitirActa()
{
    $("#cursos-form").attr("action", "CommonActaComplementariaEmitirActa").attr("target", "_self").submit();
}

$(document).ready(function () {
    $("#emitir-button").click(emitirActa);
    $("#search-button").click(getCursos);

    const msgError = $("#msg-dummy-error-div").text().replace(/(\r\n|\n|\r)/g, "");
    if (msgError !== '')
    {
        $("#msg-result-div").html("<div class='actionError'><ul><li><span>" + msgError + "</li></ul></span></div>");
        $("#result").modal('show');
    }

    const msgOK = $("#msg-dummy-ok-div").text().replace(/(\r\n|\n|\r)/g, "");
    if (msgOK !== '')
    {
        $("#msg-result-div").html("<div class='actionMessage'><ul><li><span>" + msgOK + "</li></ul></span></div>");
        $("#result").modal('show');
    }

    $("#cursos-form :input").keydown(function (e) {
        if (enterKey(e)) {
            $("#cursos-form").valid();
            const fields = $(this).parents('form:eq(0),body').find('button,input,textarea,select');
            const index = fields.index(this);
            if (index > -1 && (index + 1) < fields.length) {
                fields.eq(index + 1).focus();
            }
        }
    });

    $("#cursos-form").validate({
        validationEventTriggers: "blur",
        event: "blur",
        rules: {},
        messages: {}
    });

    $("#cursos-form :input").each(function () {
        const field_name = $(this).attr("id");
        if (field_name.startsWith("nota_")) {
            $(this).rules("add", {
                formatoNota: true
            });
        }
    });

    $("#cursos-form :input").on("change", function () {
        const $field = $(this);
        const fieldId = $field.attr("id");

        if (fieldId.startsWith("nota_")) {
            let value = $field.val().replace(",", ".");
            value = parseFloat(value);

            if (value > 10) {
                value /= 10;
            }

            const newClass = value < 4
                    ? "reprobado"
                    : "aprobado";

            $field
                    .val(value) // Actualiza el valor si fue modificado
                    .attr("class", `form-control no-padding col-lg-6 col-sm-12 ${newClass}`);
        }
    });


});



