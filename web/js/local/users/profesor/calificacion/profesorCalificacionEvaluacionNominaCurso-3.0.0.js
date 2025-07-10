

function save() {
    if ($("#nomina-form").valid()) {
        $("#nomina-form").attr("action", "ProfesorEvaluacionSaveAlumnoEvaluacion");
        $("#nomina-form").attr("onsubmit", "return true;");
        $("#nomina-form").submit();

    }
}

$(document).ready(function () {

    //Handlers
    $("#save-button").click(save);

    $("#nomina-form :input").keydown(function (e) {
        if (enterKey(e)) {
            $("#nomina-form").valid();
            const fields = $(this).parents('form:eq(0),body').find('button,input,textarea,select');
            const index = fields.index(this);
            if (index > -1 && ( index + 1 ) < fields.length) {
                fields.eq(index + 1).focus();
            }
        }
    });

    $("#nomina-form").validate({
        validationEventTriggers: "blur",
        success: function (label) {
            const id = label.attr("for");
            const input = $("#" + id);
            const value = input.val();

            (value < 4) ? input.attr("class", "reprobado") : input.attr("class", "aprobado");
        },
        event: "blur",
        rules: {},
        messages: {}
    });

    $("#nomina-form :input").each(function () {
        const field_name = $(this).attr("id");
        if (field_name.startsWith("row_")) {
            $(this).rules("add", {
                formatoNota: true
            });
        }
    });
});