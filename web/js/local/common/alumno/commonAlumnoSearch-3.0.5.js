
function clearAlumnosForm() {
    $("#rut").val("");
    $("#search-content-iframe").attr('src', "");
    $("#alumnos-form").resetForm();
}

function searchAlumno() {
    if ($("#alumnos-form").validate().form() === true) {
        $("#search-content-iframe").attr("src", 'CommonAlumnoSearch?' + $("#alumnos-form").serialize());
    }
}

$(document).ready(function () {
    //Handlers
    $("#search-button").click(searchAlumno);
    $("#clear-button").click(clearAlumnosForm);
    $("#rutdv").keypress(function (e) {
        if (enterKey(e)) {
            const rut = formatear($(this).val(), true);
            $(this).val(rut);
            validateRutDv("rutdv", "rut");
            searchAlumno();
        }
    });

    $("#rutdv").keyup(function () {
        const rut = formatear($(this).val(), true);
        $(this).val(rut);
    });
    

    $("#paterno").keypress(function (e) {
        if (enterKey(e)) {
            searchAlumno();
        }
    });
    $("#materno").keypress(function (e) {
        if (enterKey(e)) {
            searchAlumno();
        }
    });
    $("#nombre").keypress(function (e) {
        if (enterKey(e)) {
            searchAlumno();
        }
    });

    jQuery.validator.addMethod("rutdv", function (value, element) {
        const regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        return this.optional(element) || (regExp.test(jQuery.trim(value)) && validateRutDv("rutdv", "rut"));
    }, "Rut-Dv inválido");

    $("#alumnos-form").validate({
        rules: {
            rutdv: {
                rutdv: true
            }
        }
    });
});