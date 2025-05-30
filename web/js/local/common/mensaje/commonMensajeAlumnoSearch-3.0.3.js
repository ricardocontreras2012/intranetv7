function searchMessageAlumno() {
    var data_string = $("#alumno-search-form").serialize();
    $('#search-content-iframe').attr("src", 'CommonMensajeAlumnoSearch?' + data_string);
}

$(document).ready(function () {
    $("#rutdv").keypress(function (e) {
        if (enterKey(e)) {
            var rut = formatear($(this).val(), true);           
            $(this).val(rut);
            validateRutDv("rutdv", "rut")
            searchMessageAlumno();
        }
    });

    $("#rutdv").keyup(function () {
        var rut = formatear($(this).val(), true);
        $(this).val(rut);
    });

    $("#paterno").keypress(function (e) {
        if (enterKey(e)) {
            searchMessageAlumno();
        }
    });
    $("#materno").keypress(function (e) {
        if (enterKey(e)) {
            searchMessageAlumno();
        }
    });
    $("#nombre").keypress(function (e) {
        if (enterKey(e)) {
            searchMessageAlumno();
        }
    });

    jQuery.validator.addMethod("rutdv", function (value, element) {
        var regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        return this.optional(element) || (regExp.test(jQuery.trim(value)) && validateRutDv("rutdv", "rut"));
    }, "Rut-Dv inválido");

    $("#alumno-search-form").validate({
        rules: {
            rutdv: {
                rutdv: true
            }
        }
    });
});