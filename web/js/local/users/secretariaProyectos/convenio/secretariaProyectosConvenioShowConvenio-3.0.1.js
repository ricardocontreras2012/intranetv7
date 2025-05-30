function print()
{
    $("#convenio-form").attr("action", "SecretariaProyectosConvenioPrint");
    $("#convenio-form").attr("target", "_self");
    $("#convenio-form").submit();
}

$(document).ready(function () {
    $("#print-button").click(print);

    $('#tipoMonto').attr("style", "pointer-events: none;");
    $('#tipoContrato').attr("style", "pointer-events: none;");
});



