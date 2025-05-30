
function clearProfesorForm() {
    $("#rut").val("");
    $("#search-content-iframe").attr('src', "");
    $("#profesores-form").resetForm();
}

function searchProfesor() {
    if ($("#profesores-form").validate().form() === true) {
        $("#search-content-iframe").attr("src", 'CommonProfesorSearch?' + $("#profesores-form").serialize());
    }
}

$(document).ready(function () {
    //Handlers
    $("#search-button").click(searchProfesor);
    $("#clear-button").click(clearProfesorForm);
    $("#rutdv").keypress(function (e) {
        if (enterKey(e)) {
            var rut = formatear($(this).val(), true);           
            $(this).val(rut);
            validateRutDv("rutdv", "rut")
            searchProfesor();
        }
    });

    $("#rutdv").keyup(function () {
        var rut = formatear($(this).val(), true);
        $(this).val(rut);
    });

    $("#paterno").keypress(function (e) {
        if (enterKey(e)) {
            searchProfesor();
        }
    });
    $("#materno").keypress(function (e) {
        if (enterKey(e)) {
            searchProfesor();
        }
    });
    $("#nombre").keypress(function (e) {
        if (enterKey(e)) {
            searchProfesor();
        }
    });

    jQuery.validator.addMethod("rutdv", function (value, element) {
        var regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        return this.optional(element) || (regExp.test(jQuery.trim(value)) && validateRutDv("rutdv", "rut"));
    }, "Rut-Dv inválido");

    $("#profesores-form").validate({
        rules: {
            rutdv: {
                rutdv: true
            }
        }
    });
});