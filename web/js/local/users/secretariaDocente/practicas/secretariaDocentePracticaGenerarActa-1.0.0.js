

function generarActa() {
    if (formularioEsValido()) {
        $("#confirmacion").dialog("open");
        return false;
    } else
    {
        $("#msg").dialog("open");
        return false;
    }
}

$(document).ready(function () {
    var check;
    var pos;

    $("#emitir-button").click(generarActa);

    //Colorea elemento seleccionado
    $("table").on("click", ":checkbox", function ()
    {
        if ($(this).is(':checked'))
        {
            $(this).parents("tr:first").data('prevColor', $(this).parents("tr:first").css('background-color'));
            $(this).parents("tr:first").css('background-color', '#00FF33');
            check = $(this).attr("id");
            pos = parseInt(check.substr(check.indexOf("_") + 1));
            $("#cursada_" + pos).removeAttr("disabled");
            $("#nota_" + pos).removeAttr("disabled");
        } else
        {
            $("#cursada_" + pos).attr("disabled", "disabled");
            $("#nota_" + pos).attr("disabled", "disabled");
            $(this).parents("tr:first").css('background-color', '#F0FAFF');
        }
    });

    $("#convalidacion-form :input").keydown(function (e) {
        if (enterKey(e)) {
            $("#convalidacion-form").valid();
            var fields = $(this).parents('form:eq(0),body').find('button,input,textarea,select');
            var index = fields.index(this);
            if (index > -1 && (index + 1) < fields.length) {
                fields.eq(index + 1).focus();
            }
        }
    });

    $("#convalidacion-form :input").each(function (e) {
        if ($(this).attr("id").startsWith("cursada_") || $(this).attr("id").startsWith("nota_"))
        {
            $(this).attr("disabled", "disabled");
        }
    });

    $("#confirmacion").dialog({
        autoOpen: false,
        resizable: false,
        modal: true,
        width: 300,
        buttons: {
            "NO": function () {
                $(this).dialog("close");
            },
            "SI": function () {
                $("#convalidacion-form").attr("action", "SecretariaDocenteConvalidacionGenerarActa");
                $("#convalidacion-form").attr("target", "_self");
                $("#convalidacion-form").submit();

                $(this).dialog("close");
            }
        }
    });

    $("#msg").dialog({
        autoOpen: false,
        resizable: false,
        modal: true,
        width: 300,
        buttons: {
            "OK": function () {
                $(this).dialog("close");
            }
        }
    });
});


function formularioEsValido()
{
    var errStr = "";
    var retValue = false;
    var notChecked = true;

    $("#convalidacion-form :input").each(function () {
        var field_name = $(this).attr("id");
        var pos;
        var nota;
        var cursada;

        if (errStr.length === 0 && field_name.startsWith("ck_") && $("#" + field_name).prop("checked")) {
            if (notChecked)
            {
                notChecked = false;
            }
            pos = parseInt(field_name.substr(field_name.indexOf("_") + 1));
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
                            errStr = "Valor "+ nota +" está fuera de rango en la fila " + (pos + 1);
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
        $("#msg").html(errStr);
    } else
    {
        if (notChecked)
        {
            $("#msg").text("Debe convalidar al menos una asignatura");
        } else
        {
            retValue = true;
        }
    }

    return retValue;
}

