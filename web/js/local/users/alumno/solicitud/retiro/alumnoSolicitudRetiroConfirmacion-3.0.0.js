

function action(urlVar) {
    $("#confirmacion-form").attr("action", urlVar).attr("target", "_self").submit();
}

function aceptar() {
    action('AlumnoSolicitudRetiro?key=' + $("#key").val());
    $("#modal-confirmacion").modal('hide');
    $("#modal-ok").modal('show');
}

$(document).ready(function () {
    $("#modal-confirmacion").modal('show');
});

