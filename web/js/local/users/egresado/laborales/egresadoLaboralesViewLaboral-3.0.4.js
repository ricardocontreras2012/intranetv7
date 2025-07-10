

function saveReport() {
    $("#egresado-form").attr("action", "EgresadoLaboralesUpdateLaboral");
    $("#egresado-form").submit();
}

function modalEmpleador() {
    $("#new-div").dialog({
        autoOpen: true,
        resizable: false,
        modal: true,
        width: 600,
        buttons: {
            "Aceptar": function () {
                $("#rutEmpleador").val($("#empleadorLista").val());
                $(this).dialog("close");
                infoEmpleador();
            }
        }
    });
    return false;
}

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
}

function infoEmpleador() {
    $.get('CommonEmpleadorGetEmpleadorxRutEgresado', {'rut': $("#rutEmpleador").val(), 'key': $("#keyDummy").val()}, function (data) {
        $("#infoEmpleador").html(data);
        if ($("#nombreEmpleador").val().trim().length === 0) {
            $("#msg-div").html("Rut Erroneo.<br/>Si desconoce el rut de la empresa, realice una búsqueda por nombre de empleador.");
            $("#msg-div").dialog("open");
            $("#rutEmpleador").val("");
        }
    });
}

$(document).ready(function () {

    //Handlers
    $("#save-button").click(saveReport);
    $("#open-modal-button").click(modalEmpleador);
    $("#search-name-button").click(buscarNombre);

    $("#indepActividadEconomica option[value='" + $("#indepActividadEconomicaDummy").val() + "']").prop("selected", true);
    $("#region option[value='" + $("#regionDummy").val() + "']").prop("selected", true);
    $("#comuna option[value='" + $("#comunaDummy").val() + "']").prop("selected", true);
    $("#areaTrabajo option[value='" + $("#areaTrabajoDummy").val() + "']").prop("selected", true);
    $("#tipoTrabajo option[value='" + $("#tipoTrabajoDummy").val() + "']").prop("selected", true);
    $("#sueldo option[value='" + $("#sueldoDummy").val() + "']").prop("selected", true);
    $("#desdeAgno option[value='" + $("#desdeAgnoDummy").val() + "']").prop("selected", true);
    $("#desdeMes option[value='" + $("#desdeMesDummy").val() + "']").prop("selected", true);
    $("#hastaAgno option[value='" + $("#hastaAgnoDummy").val() + "']").prop("selected", true);
    $("#hastaMes option[value='" + $("#hastaMesDummy").val() + "']").prop("selected", true);
    $("#desdeAgnoEmpresa option[value='" + $("#desdeAgnoEmpresaDummy").val() + "']").prop("selected", true);
    $("#desdeMesEmpresa option[value='" + $("#desdeMesEmpresaDummy").val() + "']").prop("selected", true);
    $("#hastaAgnoEmpresa option[value='" + $("#hastaAgnoEmpresaDummy").val() + "']").prop("selected", true);
    $("#hastaMesEmpresa option[value='" + $("#hastaMesEmpresaDummy").val() + "']").prop("selected", true);

    if ($("#rutEmpleadorDummy").val() === "0") {
        $("#filaTitulo").css("display", "none");
        $("#filaRutEmpleador").css("display", "none");
        $("#filaBuscarEmpleador").css("display", "none");
        $("#filaNombreEmpleador").remove();
        $("#filaTipoEmpleador").remove();
        $("#filaActividadEconomica").remove();
        $("#dependiente[value=I]").prop("checked", true);
    } else {
        $("#filaTitulo").css("display", "table-row");
        $("#filaRutEmpleador").css("display", "table-row");
        $("#filaIndepActividadEconomica").css("display", "none");
        $("#dependiente[value=D]").prop("checked", true);
    }
    if ($("#otroLugar").val().trim().length === 0) {
        $("#filaRegion").css("display", "table-row");
        $("#filaComuna").css("display", "table-row");
        $("#filaOtroLugar").css("display", "none");
        $("#lugar[value=Chile]").prop("checked", true);
    } else {
        $("#filaRegion").css("display", "none");
        $("#filaComuna").css("display", "none");
        $("#filaOtroLugar").css("display", "table-row");
        $("#lugar[value=Extranjero]").prop("checked", true);
    }

    $("input[id='dependiente']").change(function () {
        $("#nombre").val("");
        $("#tipoEmpleador option").eq(0).prop("selected", true);
        if ($("input[id='dependiente']:checked").val() === "I") {
            $("#rutEmpleador").val("0");
            $("#filaTitulo").css("display", "none");
            $("#filaRutEmpleador").css("display", "none");
            $("#filaBuscarEmpleador").css("display", "none");
            $("#filaNombreEmpleador").remove();
            $("#filaTipoEmpleador").remove();
            $("#filaActividadEconomica").remove();
            $("#filaIndepActividadEconomica").css("display", "table-row");
        } else {
            $("#rutEmpleador").val("");
            $("#filaTitulo").css("display", "table-row");
            $("#filaRutEmpleador").css("display", "table-row");
            $("#filaBuscarEmpleador").css("display", "table-row");
            $("#filaIndepActividadEconomica").css("display", "none");
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
            $("#filaRegion").css("display", "table-row");
            $("#filaComuna").css("display", "table-row");
            $("#filaOtroLugar").css("display", "none");
        } else {
            $("#filaRegion").css("display", "none");
            $("#filaComuna").css("display", "none");
            $("#filaOtroLugar").css("display", "table-row");
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
    $("#msg-div").dialog({
        autoOpen: false,
        resizable: false,
        modal: true,
        width: 400,
        buttons: {
            "OK": function () {
                $(this).dialog("close");
            }
        }
    });

    $("#rutEmpleador").change(function () {
        $.get('CommonEmpleadorGetEmpleadorxRutEgresado', {'rut': $(this).val(), 'key': $("#keyDummy").val()}, function (data) {
            $("#infoEmpleador").html(data);
            if ($("#nombreEmpleador").val().trim().length === 0) {
                $("#msg-div").html("Rut Erroneo.<br/>Si desconoce el rut de la empresa, realice una búsqueda por nombre de empleador.");
                $("#msg-div").dialog("open");
                $("#rutEmpleador").val("");
            }
        });
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