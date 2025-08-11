/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    //Handlers
    $("#save-button").click(grabar);
    $("#save-personales-button").click(savePersonales);
    $("#caratula-button").click(generaCaratula);
    $("#genero-button").click(generaGenero);
    $("#pago-arancel-button").click(generaPagoArancel);
    $("#aceptarRevision").change(activarSavePersonales);
    /*$("#aceptarRevision").change(function () {
     $("#expediente-fieldset").prop("disabled", !this.checked);
     });*/

    $("#personales-form").validate({
        rules: {
            direccion: {required: true},
            region: {required: true},
            comuna: {required: true},
            fechaNac: {required: true, formatoFecha: true},
            fono: {required: true},
            email: {required: true, multiemail: true},
            emailLaboral: {multiemail: true}
        },
        messages: {
            direccion: {required: jQuery.validator.messages.required},
            region: {required: jQuery.validator.messages.required},
            comuna: {required: jQuery.validator.messages.required},
            fechaNac: {required: jQuery.validator.messages.required},
            fono: {required: jQuery.validator.messages.required},
            email: {required: jQuery.validator.messages.required}
        }
    });

    $("#region").change(function () {
        $.get('CommonComunaGetComunas', {'region': $(this).val(), 'key': $("#keyDummy").val()}, function (data) {
            $("#comunas").html(data);
        });
    });

    $("#region option[value='" + $("#regionDummy").val() + "']").prop("selected", true);
    $("#comuna option[value='" + $("#comunaDummy").val() + "']").prop("selected", true);

    $(".upload-input").on("change", function () {

        const fileInput = this;
        const tdoc = $(fileInput).data("upload-id");
        const file = fileInput.files[0];
        const key = $("#key").val();
        if (!file)
            return;

        // Validación de extensión .pdf
        const fileName = file.name;
        const fileExtension = fileName.split('.').pop().toLowerCase(); // Obtiene la extensión en minúsculas

        // Verificar si la extensión del archivo es .pdf
        if (fileExtension !== "pdf") {
         alert("Por favor, seleccione un archivo PDF.");
         $(fileInput).val(""); // Limpia el input
         return; // Detiene el resto del proceso
        }

        const formData = new FormData();
        formData.append("upload", file);
        formData.append("key", key);
        formData.append("tdoc", tdoc);

        $.ajax({
            url: "AlumnoSolicitudExpedienteUploadFile",
            type: "POST",
            data: formData,
            dataType: 'json',
            contentType: false,
            processData: false,
            success: function (response) {
                if (response.retValue === "success") {
                    alert("Archivo subido exitosamente.");
                    $(fileInput).attr("data-upload-success", "true");
                    $(fileInput).addClass("is-valid");
                } else {
                    alert("Error al subir el archivo.");
                    $(fileInput).attr("data-upload-success", "false");
                }
                /*alert("Archivo subido exitosamente.");
                $(fileInput).attr("data-upload-success", "true");
                $(fileInput).addClass("is-valid");*/

                /* verifico si activo boton*/
                let allUploaded = true;
                $(".upload-input").each(function () {
                    const isUploaded = $(this).attr("data-upload-success") === "true";
                    if (!isUploaded) {
                        allUploaded = false;
                        return false; // corta el loop si encuentra uno sin subir
                    }
                });
                if (!allUploaded) {
                    console.log("Disabled: true");
                } else {
                    console.log("Disabled: false");
                    $('#btn-next-step-4').prop('disabled', false);
                }
                /* fin verificacion */


            },
            error: function () {
                $(fileInput).attr("data-upload-success", "false");
                alert("Error al subir el archivo.");
            }
        });
    });


    function mostrarPaso(n) {
        $('.paso').removeClass('activo');
        $('.paso[data-step="' + n + '"]').addClass('activo');
        window.scrollTo(0, 0);
    }

    $('.siguiente').click(function () {
        let pasoActual = parseInt($(this).closest('.paso').data('step'));
        mostrarPaso(pasoActual + 1);
    });

    $('.volver').click(function () {
        let pasoActual = parseInt($(this).closest('.paso').data('step'));
        mostrarPaso(pasoActual - 1);
    });
});

function generaCaratula() {
    $("#get-docs-form").attr("action", "AlumnoSolicitudExpedienteGeneraCaratula");
    $("#get-docs-form").submit();
}

function generaPagoArancel() {
    $("#get-docs-form").attr("action", "AlumnoSolicitudExpedienteGeneraPagoArancel");
    $("#get-docs-form").submit();
}
function generaGenero() {
    $("#get-docs-form").attr("action", "AlumnoSolicitudExpedienteGeneraSolicitudGenero");
    $("#get-docs-form").submit();
}
function grabar() {
    let allUploaded = true;

    $(".upload-input").each(function () {
        const isUploaded = $(this).attr("data-upload-success") === "true";
        if (!isUploaded) {
            allUploaded = false;
            return false; // corta el loop si encuentra uno sin subir
        }
    });

    if (!allUploaded) {
        alert("Debes subir todos los documentos requeridos antes de continuar.");
        return;
    } else {
        $('#btn-next-step-4').prop('disabled', false);
    }

    $("#solicitud-form").attr("action", "AlumnoSolicitudExpedienteAddSolicitud").attr("target", "_self").submit();
}

function activarSavePersonales() {
    //console.log('Grabar');
    $("#save-personales-button").prop("disabled", !this.checked);
}

function savePersonales() {
    var $form = $('#personales-form');

    if ($form.length === 0) {
        console.warn('Formulario no encontrado.');
        return;
    }

    // Validar el formulario
    if ($form.validate().form()) {
        var formData = $form.serialize();

        $.ajax({
            url: 'AlumnoSolicitudExpedienteUpdatePersonales', // Ruta a donde quieres enviar los datos
            type: 'POST',
            data: formData,
            dataType: 'json',
            success: function (respuesta) {
                if (respuesta.success) {
                    alert('Formulario enviado correctamente');
                    console.log('Respuesta del servidor:', respuesta);
                    $('#btn-next-step-2').prop('disabled', false);
                } else {
                    alert('Error: ' + respuesta.message);
                    console.warn('Respuesta del servidor:', respuesta);
                    $('#btn-next-step-2').prop('disabled', true);
                }
            },
            error: function (xhr, status, error) {
                alert('Error en la comunicación con el servidor. Intenta nuevamente.');
                console.error('Detalles del error:', status, error);
            }
        });
    } else {
        alert('Por favor, completa los campos obligatorios correctamente.');
    }

}