

function clearAyudanteForm() {
    $("#rut").val("");
    $("#search-content-iframe").attr('src', "");
    $("#ayudantes-form").resetForm();
}

function searchAyudante() {
    if ($("#ayudantes-form").validate().form() === true) {
        $("#search-content-iframe").attr("src", 'CommonAyudanteSearch?' + $("#ayudantes-form").serialize());
    }
}

$(document).ready(function () {

    //Handlers
    $("#search-button").click(searchAyudante);
    $("#clear-button").click(clearAyudanteForm);
    $("#rutdv").keypress(function (e) {
        if (enterKey(e)) {
            var rut = formatear($(this).val(), true);           
            $(this).val(rut);
            validateRutDv("rutdv", "rut")
            searchAyudante();
        }
    });

    $("#rutdv").keyup(function () {
        var rut = formatear($(this).val(), true);
        $(this).val(rut);
    });

    $("#paterno").keypress(function (e) {
        if (enterKey(e)) {
            searchAyudante();
        }
    });
    $("#materno").keypress(function (e) {
        if (enterKey(e)) {
            searchAyudante();
        }
    });
    $("#nombre").keypress(function (e) {
        if (enterKey(e)) {
            searchAyudante();
        }
    });

    jQuery.validator.addMethod("rutdv", function (value, element) {
        var regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        return this.optional(element) || (regExp.test(jQuery.trim(value)) && validateRutDv("rutdv", "rut"));
    }, "Rut-Dv inválido");

    $("#ayudantes-form").validate({
        rules: {
            rutdv: {
                rutdv: true
            }
        }
    });
});