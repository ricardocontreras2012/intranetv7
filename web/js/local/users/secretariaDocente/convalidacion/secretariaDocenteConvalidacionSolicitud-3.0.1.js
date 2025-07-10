

function generarActa() {
    $("#confirmacion").modal('show');
    return false;
}

function importar()
{
    $("#convalidacion-form").attr("action", "SecretariaDocenteConvalidacionEnableExcel");
    $("#convalidacion-form").attr("target", "_self");
    $("#convalidacion-form").submit();
}

function generar()
{
    $("#convalidacion-form").attr("action", "SecretariaDocenteConvalidacionGenerarActa");
    $("#convalidacion-form").attr("target", "_self");
    $("#convalidacion-form").submit();
}

function imprimir()
{
    $("#convalidacion-form").attr("action", "SecretariaDocenteConvalidacionImprimirInforme");
    $("#convalidacion-form").attr("target", "_self");
    $("#convalidacion-form").submit();
}

function grabar()
{
    if (formularioEsValido()) {
        $("#convalidacion-form").attr("action", "SecretariaDocenteConvalidacionSaveSolicitud");
        $("#convalidacion-form").attr("target", "_self");
        $("#convalidacion-form").submit();
    } else
    {
        $("#msg").modal('show');
        return false;
    }
}

$(document).ready(function () {
    let check;
    let pos;

    $("#emitir-button").click(generarActa);
    $("#importar-button").click(importar);
    $("#save-button").click(grabar);
    $("#print-button").click(imprimir);

    //Colorea elemento seleccionado
    $("table").on("click", ":checkbox", function ()
    {
        if ($(this).is(':checked'))
        {
            $(this).parents("tr:first").data('prevColor', $(this).parents("tr:first").css('background-color'));
            $(this).parents("tr:first").css('background-color', '#93E9BE');
            check = $(this).attr("id");
            pos = parseInt(check.substr(check.indexOf("_") + 1));
            $("#cursada_" + pos).removeAttr("disabled");
            $("#estado_" + pos).removeAttr("disabled");
            $("#nota_" + pos).removeAttr("disabled");
            $("#electivo_" + pos).removeAttr("disabled");
            $("#obs_" + pos).removeAttr("disabled");
        } else
        {
            $("#cursada_" + pos).attr("disabled", "disabled");
            $("#estado_" + pos).attr("disabled", "disabled");
            $("#nota_" + pos).attr("disabled", "disabled");
            $("#electivo_" + pos).attr("disabled", "disabled");
            $("#obs_" + pos).attr("disabled", "disabled");
            $(this).parents("tr:first").css('background-color', '#F0FAFF');
        }
    });

    $("#convalidacion-form :input").keydown(function (e) {
        if (enterKey(e)) {
            $("#convalidacion-form").valid();
            const fields = $(this).parents('form:eq(0),body').find('button,input,textarea,select');
            const index = fields.index(this);
            if (index > -1 && (index + 1) < fields.length) {
                fields.eq(index + 1).focus();
            }
        }
    });


    $("#convalidacion-form :input").each(function (e) {
        if ($("#estado").val() === "N")
        {
            if ($(this).attr("id").startsWith("cursada_") || $(this).attr("id").startsWith("nota_") || $(this).attr("id").startsWith("electivo_") || $(this).attr("id").startsWith("obs_") || $(this).attr("id").startsWith("estado_"))
            {

                $(this).attr("disabled", "disabled");
            }
        }
    });

    $("#convalidacion-form").validate({
        validationEventTriggers: "blur",
        success: function (label) {},
        event: "blur",
        rules: {},
        messages: {}
    });

    $("#convalidacion-form :input").each(function () {
        const field_name = $(this).attr("id");
        if (field_name.startsWith("nota_")) {
            $(this).rules("add", {
                formatoNota: true
            });
        }
    });
});


function formularioEsValido()
{
    let errStr = "";
    let retValue = false;
    let notChecked = true;

    $("#convalidacion-form :input").each(function () {
        const fieldName = $(this).attr("id");
        let pos;
        let nota;
        let cursada;

        if (errStr.length === 0 && fieldName.startsWith("ck_") && $("#" + fieldName).prop("checked")) {
            if (notChecked)
            {
                notChecked = false;
            }
            pos = parseInt(fieldName.substr(fieldName.indexOf("_") + 1));
            nota = $("#nota_" + pos).val();
            cursada = $("#cursada_" + pos).val();

            if (!isEmpty(cursada))
            {
                if (!isEmpty(nota))
                {
                    nota = nota.replace(",", ".");
                    if (!isNaN(parseFloat(nota)))
                    {
                        if (nota < 4 || nota > 7)
                        {
                            errStr = "Valor " + nota + " está fuera de rango en la fila " + (pos + 1);
                        }
                    } else
                    {
                        errStr = "Valor " + nota + " no es numérico en la fila " + (pos + 1);
                    }
                }
            } else
            {
                errStr = "Falta definir asignatura cursada en fila " + (pos + 1);
            }
        }
    });

    if (errStr.length > 0)
    {
        $("#error-div").html(errStr);
    } else
    {
        if (notChecked && $("#estado").val() === "N")
        {
            $("#error-div").text("Debe convalidar al menos una asignatura");
        } else
        {
            retValue = true;
        }
    }

    return retValue;
}

