/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    //Handlers
    $("#save-button").click(grabar);
    $("#caratula-button").click(generaCaratula);
    $("#pago-arancel-button").click(generaPagoArancel);
    $("#aceptarRevision").change(function () {
        $("#expediente-fieldset").prop("disabled", !this.checked);
    });

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