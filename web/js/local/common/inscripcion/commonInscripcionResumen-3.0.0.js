function exportResumen()
{
    $("#resumen-form").attr("action", "CommonAlumnoHorarioInscripcionPrint");
    $("#resumen-form").attr("target", "_blank");
    $("#resumen-form").submit();
}
