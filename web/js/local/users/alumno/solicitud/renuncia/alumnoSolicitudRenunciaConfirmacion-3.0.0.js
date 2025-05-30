

function action(urlVar) {
    $("#confirmacion-form").attr("action", urlVar).attr("target", "_self").submit();

}

function aceptar() {
    action('AlumnoSolicitudRenuncia?key=' + $("#key").val());
    $("#modal-confirmacion").modal('hide');
}

$(document).ready(function () {
    $("#modal-confirmacion").modal('show');
});
