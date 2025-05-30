
function saveReport() {
    if ($("#reporte-form").validate().form() === true)
    {
        $("#reporte-form").attr("action", "ProfesorReporteSaveModifiedReporte").attr("target", "_self");
        $("#reporte-form").submit();
    }
}

function exportReports() {
    $("#reporte-form").attr("action", "CommonReporteExport").submit();
}

function deleteReports() {
    $("#confirmacion").modal('show');
    return false;
}

function remove()
{
    $("#reporte-form").attr("action", "ProfesorReporteRemoveReportes").attr("target", "_self").submit();
}

$(document).ready(function () {
    //Handlers
    $("#save-button").click(saveReport);
    $("#export-button").click(exportReports);
    $("#delete-button").click(deleteReports);
    //
    jQuery("textarea").blur(function () {
        $(this).val(jQuery.trim($(this).val()));
    });

    $.validator.addMethod("fechaActual",
            function (value, element) {
                var dArr = value.split("/");
                var dArrActual = $("#fechaActual").val().split("/");
                var d = new Date(dArr[2], dArr[1] - 1, dArr[0]);
                var dActual = new Date(dArrActual[2], dArrActual[1] - 1, dArrActual[0]);

                return this.optional(element) || ((d <= dActual));
            }
    , "Fecha debe ser <= a la fecha actual.");

    $.validator.addMethod("recuperacion",
            function (value, element) {
                var dArr = value.split("/");
                var dArrActual = $("#recuperacion").val().split("/");
                var d = new Date(dArr[2], dArr[1] - 1, dArr[0]);
                var dActual = new Date(dArrActual[2], dArrActual[1] - 1, dArrActual[0]);

                return this.optional(element) || ((d <= dActual));
            }
    , "Fecha debe ser <= a la fecha actual.");

    $("#reporte-form").validate({
        event: "blur",
        rules: {
            objetivos: {
                maxlength: 1000
            },
            contenido: {
                required: true,
                maxlength: 1000
            },
            metodo: {
                maxlength: 1000
            },
            observaciones: {
                maxlength: 1000
            },
            recuperacion: {
                formatoFecha: true,
                fechaActual: true
            }
        },
        messages: {
            objetivos: {
                maxlength: jQuery.validator.messages.maxlength(1000)
            },
            contenido: {
                required: jQuery.validator.messages.required,
                maxlength: jQuery.validator.messages.maxlength(1000)
            },
            metodo: {
                maxlength: jQuery.validator.messages.maxlength(1000)
            },
            observaciones: {
                maxlength: jQuery.validator.messages.maxlength(1000)
            }
        }
    });
});