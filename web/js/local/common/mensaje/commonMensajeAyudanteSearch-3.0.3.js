function searchMessageAyudante() {
    var data_string = $("#ayudante-search-form").serialize();
    $('#search-content-iframe').attr("src", 'CommonMensajeAyudanteSearch?' + data_string);
}

$(document).ready(function () {
   $("#rutdv").keypress(function (e) {
        if (enterKey(e)) {
            var rut = formatear($(this).val(), true);           
            $(this).val(rut);
            validateRutDv("rutdv", "rut")
            searchMessageAyudante();
        }
    });

    $("#rutdv").keyup(function () {
        var rut = formatear($(this).val(), true);
        $(this).val(rut);
    });

    $("#paterno").keypress(function (e) {
        if (enterKey(e)) {
            searchMessageAyudante();
        }
    });
    $("#materno").keypress(function (e) {
        if (enterKey(e)) {
            searchMessageAyudante();
        }
    });
    $("#nombre").keypress(function (e) {
        if (enterKey(e)) {
            searchMessageAyudante();
        }
    });

    jQuery.validator.addMethod("rutdv", function (value, element) {
        var regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        return this.optional(element) || (regExp.test(jQuery.trim(value)) && validateRutDv("rutdv", "rut"));
    }, "Rut-Dv inválido");

    $("#ayudante-search-form").validate({
        rules: {
            rutdv: {
                rutdv: true
            }
        }
    });
});