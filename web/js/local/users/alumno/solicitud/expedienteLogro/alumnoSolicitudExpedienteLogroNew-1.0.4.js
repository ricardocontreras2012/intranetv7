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
    $("#pago-arancel-button").click(generaPagoArancel);
    $("#aceptarRevision").change(function () {
        $("#expediente-fieldset").prop("disabled", !this.checked);
    });

    $("#personales-form").validate({
        rules: {
            region: {required: true},
            comuna: {required: true},
            fechaNac: {required: true, formatoFecha: true},
            email: {required: true, multiemail: true},
            emailLaboral: {multiemail: true}
        },
        messages: {
            region: {required: jQuery.validator.messages.required},
            comuna: {required: jQuery.validator.messages.required},
            fechaNac: {required: jQuery.validator.messages.required},
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

        const formData = new FormData();
        formData.append("upload", file);
        formData.append("key", key);
        formData.append("tdoc", tdoc);

        $.ajax({
            url: "AlumnoSolicitudExpedienteUploadFile",
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
            success: function (response) {
                alert("Archivo subido exitosamente.");
                $(fileInput).attr("data-upload-success", "true");
                $(fileInput).addClass("is-valid");
            },
            error: function () {
                $(fileInput).attr("data-upload-success", "false");
                alert("Error al subir el archivo.");
            }
        });
    });

    $('#personales-form').on('submit', function (e) {
        e.preventDefault(); // Evita el envío tradicional

        // Validar usando jQuery Validate
        if ($(this).validate().form()) {
            // Tomar datos del formulario
            var formData = $(this).serialize(); // Codifica los campos del form (como query string)

            $.ajax({
                url: 'AlumnoMisDatosSave', // URL de destino
                type: 'POST', // Método HTTP
                data: formData, // Datos del formulario
                success: function (respuesta) {
                    // Aquí haces algo con la respuesta del servidor
                    console.log('Enviado correctamente');
                    console.log(respuesta);
                    alert('Datos guardados correctamente');
                },
                error: function (xhr, status, error) {
                    console.error('Error en la solicitud AJAX:', error);
                    alert('Ocurrió un error al guardar los datos');
                }
            });
        }
    });

    function mostrarPaso(n) {
        $('.paso').removeClass('activo');
        $('.paso[data-step="' + n + '"]').addClass('activo');
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
    $("#expediente-form").attr("action", "AlumnoSolicitudExpedienteGeneraCaratula");
    $("#expediente-form").submit();
}

function generaPagoArancel() {
    $("#expediente-form").attr("action", "AlumnoSolicitudExpedienteGeneraPagoArancel");
    $("#expediente-form").submit();
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
    }

    $("#solicitud-form").attr("action", "AlumnoSolicitudExpedienteAddSolicitud").attr("target", "_self").submit();
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
                    // Aquí puedes actualizar la UI, redirigir, etc.
                } else {
                    alert('Error: ' + respuesta.message);
                    console.warn('Respuesta del servidor:', respuesta);
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