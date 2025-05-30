

function saveReport() {
    $("#egresado-form").attr("action", "EgresadoEstudiosUpdateEstudio");
    $("#egresado-form").submit();
}

$(document).ready(function() {

//Handlers
    $("#save-button").click(saveReport);

    $("#pais option[value='" + $("#paisDummy").val() + "']").prop("selected", true);
    $("#institucionEducacional option[value='" + $("#institucionEducacionalDummy").val() + "']").prop("selected", true);
    $("#tipoEstudio option[value='" + $("#tipoEstudioDummy").val() + "']").prop("selected", true);
    $("#desdeAgno option[value='" + $("#desdeAgnoDummy").val() + "']").prop("selected", true);
    $("#desdeMes option[value='" + $("#desdeMesDummy").val() + "']").prop("selected", true);
    $("#hastaAgno option[value='" + $("#hastaAgnoDummy").val() + "']").prop("selected", true);
    $("#hastaMes option[value='" + $("#hastaMesDummy").val() + "']").prop("selected", true);
    $("#estadoEstudio option[value='" + $("#estadoEstudioDummy").val() + "']").prop("selected", true);
    $("#areaEstudio option[value='" + $("#areaEstudioDummy").val() + "']").prop("selected", true);

    if ($("#pais").val() === "152") {
        //$("#institucionEducacionalDiv").css("display", "inline");
        //$("#otraInstitucion").css("display", "none");
        if ($("#institucionEducacional").val() === ""){
            $("#otraInstitucionDiv").css("display", "block");
        } else {
            $("#otraInstitucionDiv").css("display", "none");
        }
    } else {
        $("#institucionEducacional option[value='']").prop("selected", true);
        $("#institucionEducacionalDiv").css("display", "none");
        $("#otraInstitucion").css("display", "block");
    }

    $("#pais").change(function() {
        if ($("#pais").val() === "152") {
            $("#institucionEducacionalDiv").css("display", "block");
            $("#institucionEducacional option[value='0']").prop("selected", true);
            $("#otraInstitucionDiv").css("display", "none");
            $("#otraInstitucion").val("");
        } else {
            $("#institucionEducacional option[value='']").prop("selected", true);
            $("#institucionEducacionalDiv").css("display", "none");
            $("#otraInstitucionDiv").css("display", "block");
        }
    });
    $("#institucionEducacional").change(function() {
        if ($("#institucionEducacional").val() === "") {
            $("#otraInstitucionDiv").css("display", "block");
            $("#otraInstitucion").val("");
        } else {
            $("#otraInstitucionDiv").css("display", "none");
            $("#otraInstitucion").val("");
        }
    });
    $('#hastaMes').change(function() {
        if ($("#hastaMes option:selected").val() === "") {
            $("#hastaAgno option[value='']").prop("selected", true);}
        if ( ($("#hastaMes option:selected").val() !== "") && ($("#hastaAgno option:selected").val() === "") ) {
            $("#hastaAgno option[value='2016']").prop("selected", true);}
    });
    $('#hastaAgno').change(function() {
        if ($("#hastaAgno option:selected").val() === "") {
            $("#hastaMes option[value='']").prop("selected", true);}
        if ( ($("#hastaAgno option:selected").val() !== "") && ($("#hastaMes option:selected").val() === "") ) {
            $("#hastaMes option[value='1']").prop("selected", true);}
    });

    function fechaCorrecta(value, element, param) {
        value = value.toLowerCase();
        if ((parseInt($("#desdeAgno").val()) > parseInt($("#hastaAgno").val()) ) || ((parseInt($("#desdeAgno").val()) === parseInt($("#hastaAgno").val()) ) && (parseInt($("#desdeMes").val() ) >= parseInt($("#hastaMes").val() ) ))) {
            return false;
        } else {
            return true;
        }
    }
    function institucionCorrecta(value, element, param) {
        value = value.toLowerCase();
        if (($("#institucionEducacional").val() === "0") && ($("#pais").val() === "152")) {
            return false;
        } else {
            return true;
        }
    }
    function otraInstitucionCorrecta(value, element, param) {
        value = value.toLowerCase();
        if ((($("#otraInstitucion").val().trim().length === 0) && ($("#pais").val() !== "152")) || (($("#otraInstitucion").val().trim().length === 0) && ($("#institucionEducacional").val() === ""))) {
            return false;
        } else {
            return true;
        }
    }
    function tipoEstudioCorrecta(value, element, param) {
        value = value.toLowerCase();
        if ($("#tipoEstudio").val() === "0") {
            return false;
        } else {
            return true;
        }
    }

    $.validator.addMethod("fechaCorrecta", fechaCorrecta, "La Fecha de Termino debe ser posterior a la Fecha de Inicio.");
    $.validator.addMethod("institucionCorrecta", institucionCorrecta, "Campo obligatorio.");
    $.validator.addMethod("otraInstitucionCorrecta", otraInstitucionCorrecta, "Escriba el nombre de la institución donde realizó su estudio.");
    $.validator.addMethod("tipoEstudioCorrecta", tipoEstudioCorrecta, "Seleccione tipo de estudio.");

    $("#egresado-form").validate({
        rules: {
            institucionEducacional: {
                institucionCorrecta: true
            },
            otraInstitucion: {
                otraInstitucionCorrecta: true
            },
            tipoEstudio: {
                tipoEstudioCorrecta: true
            },
            nombreEstudio: {
                required: true
            },
            hastaAgno: {
                fechaCorrecta: true
            },
            estadoEstudio: {
                required: true,
                min: 1
            },
            areaEstudio: {
                required: true,
                min: 1
            }
        },
        messages: {
            tipoEstudio: {
                min: jQuery.validator.messages.required
            },
            estadoEstudio: {
                min: jQuery.validator.messages.required
            },
            areaEstudio: {
                min: jQuery.validator.messages.required
            }
        }
    });
});