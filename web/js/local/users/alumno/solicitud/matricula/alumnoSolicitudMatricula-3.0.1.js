function avisar() {
    $("#aviso").modal("show");
}

function grabar() {
    let causa = $("#causa").val().trim();
    $("#causa").val(causa);
    
    if ($("#causa-form").validate().form() === true) {
        $("#causa-form").attr("action", "AlumnoSolicitudSaveMatricula").attr("target", "_self").submit();
    }
}

$(document).ready(function () {  
    $("#save-button").click(avisar);
    
    $("#causa-form").validate({
        rules: {
            causa: {
                required: true
            },
            upload: {
                required: true
            }                    
        }
    });
});

