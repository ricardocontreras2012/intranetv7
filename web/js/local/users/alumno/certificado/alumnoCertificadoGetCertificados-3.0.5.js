function setPos(pos, tipo) {
    $("#pos").val(pos);
    $("#tipo").val(tipo);
}

function nextStep() {
    const tipo = parseInt($("#tipo").val());   

    if ($("#certificacion-form").validate().form() === true)
    {
        switch (tipo) {
            case 1:                
                $("#certificacion-form").attr("action", "AlumnoCertificacionCheckRegular?key=" + $("#keyDummy").val());
                break;
            case 2:
                $("#certificacion-form").attr("action", "AlumnoCertificacionCheckNoImpedimentos?key=" + $("#keyDummy").val());
                break;
            case 3:
                $("#certificacion-form").attr("action", "AlumnoCertificacionCheckEgresado?key=" + $("#keyDummy").val());
                break;
            case 7:
                $("#certificacion-form").attr("action", "AlumnoCertificacionCheckInformeCalificaciones?key=" + $("#keyDummy").val());
                break;
            case 4:
            case 5:
            case 30:
            case 32:
                $("#certificacion-form").attr("action", "AlumnoCertificacionCheckLogroEnTramite?key=" + $("#keyDummy").val());
                break;
            case 11:
                $("#certificacion-form").attr("action", "AlumnoCertificacionCheckRankEgresado?key=" + $("#keyDummy").val());
                break;
            case 14:
                $("#certificacion-form").attr("action", "AlumnoCertificacionCheckRankRegular?key=" + $("#keyDummy").val());
                break;

            default:
            //
        }

        $("#certificacion-form").attr("target", "_self");
        $("#certificacion-form").submit();

        return true;
    }

    return false;
}

$(document).ready(function () {
    $("#next-button").click(nextStep);
    //
    $("#certificacion-form").validate({
        rules: {
            tipo: {required: true}
        },
        messages: {
            tipo: {
                required: jQuery.validator.messages.required
            }
        }
    });
});