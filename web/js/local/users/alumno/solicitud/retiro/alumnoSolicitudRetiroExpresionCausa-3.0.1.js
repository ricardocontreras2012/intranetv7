
function grabar() {
    if ($("#causa-form").validate().form() === true) {
        $("#causa-form").attr("action", "AlumnoSolicitudSaveRetiroExpresion").attr("target", "_self").submit();
    }
}

$(document).ready(function () {  
    $("#save-button").click(grabar);
    
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
