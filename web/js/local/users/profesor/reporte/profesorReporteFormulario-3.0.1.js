

function saveReport() {
    if ($("#reporte-form").validate().form() === true)
    {
        $("#reporte-form").attr("action", "ProfesorReporteSaveNewReporte");
        $("#reporte-form").submit();
    }
}

$(document).ready(function () {

    //Handlers
    $("#save-button").click(saveReport);

    jQuery("textarea").each(function () {
        $(this).val(jQuery.trim($(this).val()));
    });

    $.validator.addMethod("selectOption",
            function (value, element) {
                return this.optional(element) || ((value !== '1'));
            }
    , "Seleccione un módulo horario");

    /*
     $.validator.addMethod("fechaHorario",
     function (value, element) {
     var dias =['L','M','W','J','V','S','D'];
     var dArr = value.split("/");
     var d = new Date(dArr[2], dArr[1] - 1, dArr[0]);
     
     return this.optional(element) || ((dias[d.getDay()-1] === $("#moduloHorario").val().substr(0,1))?true:false);
     }
     , "D\u00eda del m\u00f3dulo seleccionado no corresponde con el d\u00eda del calendario" );
     */

    $.validator.addMethod("fechaHorario",
            function (value, element) {
                return true;
            }, '');

    $.validator.addMethod("fechaActual",
            function (value, element) {
                var dArr = value.split("/");
                var dArrActual = $("#fechaActual").val().split("/");
                var d = new Date(dArr[2], dArr[1] - 1, dArr[0]);
                var dActual = new Date(dArrActual[2], dArrActual[1] - 1, dArrActual[0]);

                return this.optional(element) || ((d <= dActual));
            }
    , "Fecha debe ser <= a la fecha actual.");


    $.validator.addMethod("fechaModulo",
            function (value, element) {
                var dias = ['D', 'L', 'M', 'W', 'J', 'V', 'S'];
                var dArr = value.split("/");
                var d = new Date(dArr[2], dArr[1] - 1, dArr[0]);

                return this.optional(element) || ((dias[d.getDay()] === $("#moduloHorario").val().substr(0, 1)));
            }
    , "Fecha no coincide con el módulo horario.");

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
            moduloHorario: {
                required: true,
                selectOption: true
            },
            fecha: {
                required: true,
                formatoFecha: true,
                fechaHorario: true,
                fechaModulo: true
            },
            objetivos: {
                required: true,
                maxlength: 1000
            },
            contenido: {
                required: true,
                maxlength: 1000
            },
            metodo: {
                required: true,
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
            moduloHorario: {
                required: jQuery.validator.messages.required
            },
            fecha: {
                required: jQuery.validator.messages.required
            },
            objetivos: {
                required: jQuery.validator.messages.required,
                maxlength: jQuery.validator.messages.maxlength(1000)
            },
            contenido: {
                required: jQuery.validator.messages.required,
                maxlength: jQuery.validator.messages.maxlength(1000)
            },
            metodo: {
                required: jQuery.validator.messages.required,
                maxlength: jQuery.validator.messages.maxlength(1000)
            },
            observaciones: {
                maxlength: jQuery.validator.messages.maxlength(1000)
            }
        }
    });


    var today;
    today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
    $('#fecha').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd/mm/yyyy',
        weekStartDay: 1,
        maxDate: today
    });

});