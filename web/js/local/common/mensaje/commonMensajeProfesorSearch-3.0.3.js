function searchMessageProfesor() {
    const data_string = $("#profesor-search-form").serialize();
    $('#search-content-iframe').attr("src", 'CommonMensajeProfesorSearch?' + data_string);
}

$(document).ready(function () {
   $("#rutdv").keypress(function (e) {
        if (enterKey(e)) {
            const rut = formatear($(this).val(), true);
            $(this).val(rut);
            validateRutDv("rutdv", "rut")
            searchMessageProfesor();
        }
    });

    $("#rutdv").keyup(function () {
        const rut = formatear($(this).val(), true);
        $(this).val(rut);
    });

    $("#paterno").keypress(function (e) {
        if (enterKey(e)) {
            searchMessageProfesor();
        }
    });
    $("#materno").keypress(function (e) {
        if (enterKey(e)) {
            searchMessageProfesor();
        }
    });
    $("#nombre").keypress(function (e) {
        if (enterKey(e)) {
            searchMessageProfesor();
        }
    });

    jQuery.validator.addMethod("rutdv", function (value, element) {
        const regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        return this.optional(element) || (regExp.test(jQuery.trim(value)) && validateRutDv("rutdv", "rut"));
    }, "Rut-Dv inválido");

    $("#profesor-search-form").validate({
        rules: {
            rutdv: {
                rutdv: true
            }
        }
    });
});