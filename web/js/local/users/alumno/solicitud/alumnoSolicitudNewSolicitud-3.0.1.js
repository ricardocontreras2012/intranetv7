
function getFormSolicitud()
{
    $("#solicitud-form").attr("action", "AlumnoSolicitudEnableForm");
    $("#solicitud-form").attr("target", "_self");
    $("#solicitud-form").submit();
}

$(document).ready(function () {
    //Handlers
    $(function () {
        $('input:radio').change(function () {
            //var val = $("input[name='tipo']:checked").val();
            getFormSolicitud();
        });
    });
});