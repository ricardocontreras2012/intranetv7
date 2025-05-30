

function setModalidad(modEval)
{
    $("#modalidad").val(modEval);
    $("#modalidad-form").attr("action", "ProfesorEvaluacionSetModalidad");
    $("#modalidad-form").submit();
}

function setRelativa()
{
    setModalidad("R");
}

function setAbsoluta()
{
    setModalidad("A");
}

$(document).ready(function() {

    //Handler
    $("#relativa-button").click(setRelativa);
    $("#absoluta-button").click(setAbsoluta);
});
