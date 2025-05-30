

function saveSolicitud() {
    if ($("#solicitud-form").validate().form() === true && validForm() === true) {
        $("#solicitud-form").attr("action", "AlumnoPracticaSavePractica");
        $("#solicitud-form").submit();

        return true;
    } else {
        return false;
    }
}

function validForm()
{
    if ($("#datos-emp-div").text().trim() === "")
    {
        alert('Falta ingresar empleador');
        return false;
    }

    if ($("#datos-aut-div").text().trim() === "")
    {
        alert('Falta ingresar autorizador de la práctica');
        return false;
    }

    return true;
}

function changeRutEmpleador() {
    if (validateRutDv("rutdvEmpleador", "rutEmp")) {

        $.get('CommonEmpleadorGetEmpleadorxRut', {'rut': $("#rutEmp").val(), 'key': $("#key").val()}, function (data) {
            $("#datos-emp-div").html(data);
        });
    }
}

function changeRutAutoriza() {
    if (validateRutDv("rutdvAutoriza", "rutAut")) {

        $.get('CommonPersonaGetPersonaxRut', {'rut': $("#rutAut").val(), 'key': $("#key").val()}, function (data) {
            $("#datos-aut-div").html(data);
        });
    }
}


$(document).ready(function () {
    //Handlers
    $("#save-button").click(saveSolicitud);

    $("#rutdvEmpleador").change(changeRutEmpleador);
    jQuery.validator.addMethod("rutdvEmpleador", function (value, element) {
        var regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        return this.optional(element) || (regExp.test(jQuery.trim(value)) && validateRutDv("rutdvEmpleador", "rut"));
    }, "RUN-DV inválido.");


    $("#rutdvEmpleador").keyup(function () {
        var rut = formatear($(this).val(), true);
        $(this).val(rut);
    });
    
    $("#rutdvAutoriza").keyup(function () {
        var rut = formatear($(this).val(), true);
        $(this).val(rut);
    });

    $("#rutdvAutoriza").change(changeRutAutoriza);
    jQuery.validator.addMethod("rutdvAutoriza", function (value, element) {
        var regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        return this.optional(element) || (regExp.test(jQuery.trim(value)) && validateRutDv("rutdvAutoriza", "rut"));
    }, "RUN-DV inválido.");

    //
    jQuery("textarea").each(function () {
        $(this).val(jQuery.trim($(this).val()));
    });

    jQuery("textarea").change(function () {
        $(this).val($(this).val().trim());
    });

    $("#region").change(function () {
        $.get('CommonComunaGetComunas', {'region': $(this).val(), 'key': $("#key").val()}, function (data) {
            $("#comunas").html(data);
        });
    });

    $("#solicitud-form").validate({
        event: "blur",
        rules: {
            rutdvEmpleador: {
                required: true
            },
            labor: {
                required: true
            },
            comuna: {
                required: true
            },
            region: {
                required: true
            },
            direccion: {
                required: true
            },
            fonoEmp: {
                required: true
            },
            rutdvAutoriza: {
                required: true
            },
            fonoAut: {
                required: true
            },
            email: {required: true, multiemail: true
            },
            calidad: {
                required: true
            },
            inicio: {
                required: true
            },
            termino: {
                required: true
            }
        }
    });

    $('#inicio').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd/mm/yyyy',
        weekStartDay: 1,
        maxDate: function () {
            return $('#termino').val();
        }
    });

    $('#termino').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd/mm/yyyy',
        weekStartDay: 1,
        minDate: function () {
            return $('#inicio').val();
        }
    });

});