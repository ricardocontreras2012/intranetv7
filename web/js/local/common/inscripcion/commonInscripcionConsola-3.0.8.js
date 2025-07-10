function removeInscripcion() {
    $("#confirmacion-elimina").modal('hide');
    const data_string = $("#inscripcion-iframe").contents().find("#inscripcion-form").serialize();
    $('#inscripcion-iframe').attr("src", 'CommonInscripcionRemoveInscripcion?' + data_string);

}

function inscribir()
{
    $("#msg-confirmacion").modal('hide');
    $('#inscripcion-iframe').attr("src", 'CommonInscripcionNewInscripcion?key=' + $("#key").val());
}


function getInscripcion()
{
    const data_string = $("#inscripcion-form").serialize();
    $("#inscripcion-iframe").attr("src", 'CommonInscripcionGetInscripcion?' + data_string);
}

$(document).ready(function () {
    getInscripcion();
});
