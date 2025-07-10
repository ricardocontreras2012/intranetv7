$(document).ready(function() {
    $("#caratula-button").click(generaCaratula);
    $("#pago-arancel-button").click(generaPagoArancel);
    
    $('[data-bs-toggle="tooltip"]').tooltip();

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
                $(fileInput).addClass("is-valid");
                $(fileInput).attr("data-upload-success", "true");
                alert("Archivo subido exitosamente.");
                location.reload();
                
            },
            error: function () {
                $(fileInput).attr("data-upload-success", "false");
                alert("Error al subir el archivo.");
            }
        });
    });
    
    // Verificación Paso 2
    var allVerifiedStep2 = true;
    // Itera sobre los elementos <span> con el atributo "data-estado"
    $('span[data-estado-al]').each(function() {
        if ($(this).data('estado-al') !== 2) {
            allVerifiedStep2 = false;
            abrirPaso(2);
            return false;
        }
    });

    if (allVerifiedStep2) {
        $('#step-2').addClass('completed');
    } else {
        $('#step-2').removeClass('completed');
        return false;
    }
    
    // Verificación Paso 3
    var allVerifiedStep3 = true;
    $('span[data-estado-us]').each(function() {
        if ($(this).data('estado-us') !== 2) {
            allVerifiedStep3 = false;
            abrirPaso(2);
            return false;
        }
    });
    if (allVerifiedStep3) {
        $('#step-3').addClass('completed');
    } else {
        $('#step-3').removeClass('completed');
        return false;
    }

    // Verificación Paso 4
    if ($('#exp-estado').val() === 'TR'){
        $('#step-4').addClass('completed');
    } else {
        $('#step-4').removeClass('completed');
        abrirPaso(3);
        return false;
    }
});


function generaCaratula() {
    $("#expediente-form").attr("action", "AlumnoSolicitudExpedienteGeneraCaratula");
    $("#expediente-form").submit();
}

function generaPagoArancel() {
    $("#expediente-form").attr("action", "AlumnoSolicitudExpedienteGeneraPagoArancel");
    $("#expediente-form").submit();
}

function abrirPaso(numero) {
    console.log("Llamada paso");
    // Cierra todos los paneles primero (opcional, si usas data-bs-parent)
    const paneles = document.querySelectorAll('.accordion-collapse');
    paneles.forEach(p => {
      const instancia = bootstrap.Collapse.getOrCreateInstance(p);
      instancia.hide();
    });

    // Abre el panel deseado
    const target = document.getElementById(`collapse${numero}`);
    const collapseInstance = bootstrap.Collapse.getOrCreateInstance(target);
    collapseInstance.show();
}

