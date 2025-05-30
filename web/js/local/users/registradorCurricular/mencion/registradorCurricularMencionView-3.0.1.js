function saveParams()
{
    $("#parametros-form").attr("action", "RegistradorCurricularParametrosxMencionSave");
    $("#parametros-form").attr("target", "_self").submit();
}

$(document).ready(function () {
    $("#save-button").click(saveParams);
});


