
function buscar()
{
    $("#acta-form").attr("action", "CommonPracticaActaEnable").attr("target", "_self").submit();
}

function emitirActa()
{
    if (parseInt($("#porc_emp").val()) + parseInt($("#porc_coord").val()) !== 100) {
        $("#modal-error").modal('show');
    } else {
        $("#modal-confirmacion").modal('show');
    }
}

function emitirActaAceptar() {
    $("#modal-confirmacion").modal('hide');
    $("#acta-form").attr("action", "CommonPracticaActaEmitir").attr("target", "_self").submit();
}

$(document).ready(function () {

    $("#emitir-button").click(emitirActa);
    $("#buscar-button").click(buscar);
    $("#acta-form").validate({
        rules: {
            agno: {
                required: true,
                digits: true
            },
            sem: {
                required: true,
                digits: true
            },
            porc_emp: {
                required: true,
                digits: true
            },
            porc_coord: {
                required: true,
                digits: true
            }
        },
        messages: {
            agno: {
                required: jQuery.validator.messages.required
            },
            sem: {
                required: jQuery.validator.messages.required
            },
            porc_emp: {
                required: jQuery.validator.messages.required
            },
            porc_coord: {
                required: jQuery.validator.messages.required
            }
        }
    });
});
