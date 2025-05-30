function imprimirActaExamen()
{
    $("#acta-form").attr("action", "SecretariaDocenteGraduacionTitulacionPrintActa");
    $("#acta-form").attr("target", "_blank");
    $("#acta-form").submit();
}


