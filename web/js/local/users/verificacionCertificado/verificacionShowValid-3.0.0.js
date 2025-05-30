

function back() {
    $("#certificado-form").attr("action", "/intranetv7/jsp/users/verificacionCertificado/verificacionCertificado.jsp");
    $("#certificado-form").submit();
}

$(document).ready(function () {

    //Handler
    $("#back-button").click(back);
//
});