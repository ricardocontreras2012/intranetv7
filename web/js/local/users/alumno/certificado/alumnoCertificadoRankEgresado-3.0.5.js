function showConfirm()
{
    if ($("#certificacion-form").validate().form() === true)
    {
        $("#tram-div").html($("#tramite option:selected").text());
        $("#glosa-div").html($("#obs").val());
        $("#confirm-dialog").modal('show');
    }
}

function emitirCertificado() {
    if ($("#certificacion-form").validate().form() === true)
    {
        $("#certificacion-form").attr("action", "AlumnoCertificacionGenerarRankEgresado?key=" + $("#keyDummy").val());
        $("#certificacion-form").attr("target", "_blank");
        $("#certificacion-form").submit();
        
        document.getElementsByTagName("BODY")[0].style.display = "none";
        return true;
    }
    return false;
}

$(document).ready(function () {       
    //Handler
    $("#print-button").click(showConfirm);
    //
    $("#certificacion-form").validate({
        rules: {
            tramite: {required: true},
            rank: {required: true}
        },
        messages: {
            
            tramite: {
                required: jQuery.validator.messages.required
            },
            rank: {
                required: jQuery.validator.messages.required
            }
        }
    });

});