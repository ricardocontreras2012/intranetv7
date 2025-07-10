

function saveReport() {
    $("#egresado-form").attr("action", "EgresadoLaboralesSaveLaboral");
    $("#egresado-form").submit();
}

function modalEmpleador() {
    $("#modal-empleador").modal('show');
    return true;
}

function  modalEmpleadores() {
    //$("#new-evaluacion-div").html("<table><tr><td>EvaluaciÃ³n</td><td>" + medio + "</td></tr><tr><td>Porcentaje</td><td>" + porc + "</td></tr></table>");
    $("#modal-empleadores").modal('show');

    return false;
}

function modalEmpleadoresAceptar()
{
    const $aux = $("input[name='aemCorrel']:checked").val();
    $("#correlAluEmp").val($aux);
    $("#rutEmpleador").val($("#rut_" + $aux).val());
    $("#desdeMesEmpresa").val($("#dme_" + $aux).val());
    $("#desdeAgnoEmpresa").val($("#dag_" + $aux).val());
    $("#hastaMesEmpresa").val($("#hme_" + $aux).val());
    $("#hastaAgnoEmpresa").val($("#hag_" + $aux).val());
    infoEmpleador();
    $("#modal-empleadores").modal('hide');
}

/*
 function buscarNombre() {
 $("#nombre").val($.trim($("#nombre").val()));
 if ($("#nombre").val().length >= 3) {
 $.get('CommonEmpleadorGetEmpleadoresxNombre', {'nombre': $("#nombre").val(), 'key': $("#keyDummy").val()}, function (data) {
 $("#listadoEmpleadores").html(data);
 });
 } else {
 $("#msg-div").html("Ingrese mínimo de 3 caracteres");
 $("#msg-div").dialog("open");
 }
 }*/

function infoEmpleador() {
    $.get('CommonEmpleadorGetEmpleadorxRutEgresado', {'rut': $("#rutEmpleador").val(), 'key': $("#keyDummy").val()}, function (data) {
        $("#infoEmpleador").html(data);
        if ($("#nombreEmpleador").val().trim().length === 0) {
            //$("infoEmpleador").html("Rut Erroneo.<br/>Si desconoce el rut de la empresa, realice una búsqueda por nombre de empleador.");
            $("#rutEmpleador").val("");
        }
    });
}

$(document).ready(function () {

    const aux = $(".aemCorrel");
    if (aux.length >= 1)
        modalEmpleadores();
    //Handlers

    $("#save-button").click(saveReport);
    $("#empleadores-button").click(modalEmpleadores);
    //$("#open-modal-button").click(modalEmpleador);
    //$("#search-name-button").click(buscarNombre);

    const monthNames = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
    for (let month = 0; month < monthNames.length; month++) {
        $('#desdeMesEmpresa').append('<option value="' + parseInt(month + 1) + '">' + monthNames[month] + '</option>');
        $('#hastaMesEmpresa').append('<option value="' + parseInt(month + 1) + '">' + monthNames[month] + '</option>');
        $('#desdeMes').append('<option value="' + parseInt(month + 1) + '">' + monthNames[month] + '</option>');
        $('#hastaMes').append('<option value="' + parseInt(month + 1) + '">' + monthNames[month] + '</option>');
    }

    $("input:radio[name='dependiente'][value ='" + $("#dependienteDummy").val() + "']").prop('checked', true);
    $("input:radio[name='lugar'][value ='" + $("#lugarDummy").val() + "']").prop('checked', true);
    $("#filaOtroLugar").css("display", "none");
    $("#filaIndepActividadEconomica").css("display", "none");

    $("input[id='dependiente']").change(function () {
        $("#nombre").val("");
        $("#tipoEmpleador option").eq(0).prop("selected", true);
        if ($("input[id='dependiente']:checked").val() === "I") {
            $("#filaDependiente").css("display", "none");
            /*$("#rutEmpleador").val("0");
             $("#filaTitulo").css("display", "none");
             $("#filaRutEmpleador").css("display", "none");
             $("#filaBuscarEmpleador").css("display", "none");
             $("#filaNombreEmpleador").remove();
             $("#filaTipoEmpleador").remove();
             $("#filaActividadEconomica").remove();*/
            $("#filaIndepActividadEconomica").css("display", "block");
        } else {
            $("#rutEmpleador").val("");
            $("#filaDependiente").css("display", "block");
            /*$("#filaTitulo").css("display", "block");
             $("#filaRutEmpleador").css("display", "block");
             $("#filaBuscarEmpleador").css("display", "block");*/
            $("#filaIndepActividadEconomica").css("display", "none");
            $("#indepActividadEconomica").val(0);
        }
    });

    $("#rutEmpleador").keyup(function () {
        const rut = formatear($(this).val(), true);
        $(this).val(rut);
    });
    
    $("input[id='lugar']").change(function () {
        $("#region option").eq(0).prop("selected", true);
        $("#comuna option").eq(0).prop("selected", true);
        $("#otroLugar").val("");
        if ($("input[id='lugar']:checked").val() === "Chile") {
            $("#filaRegion").css("display", "block");
            $("#filaComuna").css("display", "block");
            $("#filaOtroLugar").css("display", "none");
        } else {
            $("#filaRegion").css("display", "none");
            $("#filaComuna").css("display", "none");
            $("#filaOtroLugar").css("display", "block");
        }
    });
    $("#region").change(function () {
        $.get('CommonComunaGetComunas', {'region': $(this).val(), 'key': $("#keyDummy").val()}, function (data) {
            $("#comunas").html(data);
        });
    });
    $('#hastaMes').change(function () {
        if ($("#hastaMes option:selected").val() === "") {
            $("#hastaAgno option[value='']").prop("selected", true);
        }
        if (($("#hastaMes option:selected").val() !== "") && ($("#hastaAgno option:selected").val() === "")) {
            $("#hastaAgno option[value='2016']").prop("selected", true);
        }
    });
    $('#hastaAgno').change(function () {
        if ($("#hastaAgno option:selected").val() === "") {
            $("#hastaMes option[value='']").prop("selected", true);
        }
        if (($("#hastaAgno option:selected").val() !== "") && ($("#hastaMes option:selected").val() === "")) {
            $("#hastaMes option[value='1']").prop("selected", true);
        }
    });
    $('#hastaMesEmpresa').change(function () {
        if ($("#hastaMesEmpresa option:selected").val() === "") {
            $("#hastaAgnoEmpresa option[value='']").prop("selected", true);
        }
        if (($("#hastaMesEmpresa option:selected").val() !== "") && ($("#hastaAgnoEmpresa option:selected").val() === "")) {
            $("#hastaAgnoEmpresa option[value='2016']").prop("selected", true);
        }
    });
    $('#hastaAgnoEmpresa').change(function () {
        if ($("#hastaAgnoEmpresa option:selected").val() === "") {
            $("#hastaMesEmpresa option[value='']").prop("selected", true);
        }
        if (($("#hastaAgnoEmpresa option:selected").val() !== "") && ($("#hastaMesEmpresa option:selected").val() === "")) {
            $("#hastaMesEmpresa option[value='1']").prop("selected", true);
        }
    });

    $("#rutEmpleador").change(function () {
        infoEmpleador();
    });

    function rutEmpleadorCorrecta(value, element, param) {
        value = value.toLowerCase();
        if (($("input[id='dependiente']:checked").val() === "D") && ($("#rutEmpleador").val().trim().length === 0)) {
            return false;
        } else {
            return true;
        }
    }
    function lugarCorrecta(value, element, param) {
        value = value.toLowerCase();
        if (($("input[id='lugar']:checked").val() === "Extranjero") && ($("#otroLugar").val().trim().length === 0)) {
            return false;
        } else {
            return true;
        }
    }
    function fechaCorrecta(value, element, param) {
        value = value.toLowerCase();
        if ((parseInt($("#desdeAgno").val()) > parseInt($("#hastaAgno").val())) || ((parseInt($("#desdeAgno").val()) === parseInt($("#hastaAgno").val())) && (parseInt($("#desdeMes").val()) >= parseInt($("#hastaMes").val())))) {
            return false;
        } else {
            return true;
        }
    }
    function fechaEmpresaCorrecta(value, element, param) {
        value = value.toLowerCase();
        if ((parseInt($("#desdeAgnoEmpresa").val()) > parseInt($("#hastaAgnoEmpresa").val())) || ((parseInt($("#desdeAgnoEmpresa").val()) === parseInt($("#hastaAgnoEmpresa").val())) && (parseInt($("#desdeMesEmpresa").val()) >= parseInt($("#hastaMesEmpresa").val())))) {
            return false;
        } else {
            return true;
        }
    }
    $.validator.addMethod("rutdv", function (value, element) {
        const regExp = new RegExp("^[0-9.]+-?(\\d{1}|[Kk])$");
        return this.optional(element) || (regExp.test(jQuery.trim(value)) && validateRutDv("rutEmpleador", "rut"));
    }, "Rut-Dv inválido");
    $.validator.addMethod("rutEmpleadorCorrecta", rutEmpleadorCorrecta, "Campo obligatorio.");
    $.validator.addMethod("lugarCorrecta", lugarCorrecta, "Campo obligatorio.");
    $.validator.addMethod("fechaCorrecta", fechaCorrecta, "Fecha de Término debe ser posterior a la Fecha de Inicio.");
    $.validator.addMethod("fechaEmpresaCorrecta", fechaEmpresaCorrecta, "Fecha de Término debe ser posterior a la Fecha de Inicio.");

    $("#egresado-form").validate({
        rules: {
            /*rutEmpleador: {
             rutEmpleadorCorrecta: true
             },
             rutEmpleador: {
             rutdv: true
             },
             tipoEmpleador: {
             required: true
             },
             indepActividadEconomica: {
             required: true,
             min: 1
             },*/
            otroLugar: {
                lugarCorrecta: true
            },
            areaTrabajo: {
                required: true,
                min: 1
            },
            cargo: {
                required: true
            },
            tipoTrabajo: {
                required: true,
                min: 1
            },
            sueldo: {
                number: true
            },
            hastaAgno: {
                fechaCorrecta: true
            },
            hastaAgnoEmpresa: {
                fechaEmpresaCorrecta: true
            }
        },
        messages: {
            areaTrabajo: {
                min: jQuery.validator.messages.required
            },
            tipoTrabajo: {
                min: jQuery.validator.messages.required
            }
        }
    });
});