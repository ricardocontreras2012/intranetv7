function reincorpora(tipo)
{
    //$.blockPage();
    var data_string = {'key': $("#keyDummy").val(), 'tipo': tipo};
    jQuery.ajax({
        url: "RegistradorCurricularReincorporacion",
        type: "POST",
        data: data_string,
        success: function (data) {
            var iframe = document.getElementById('main-content-iframe');
            iframe.contentWindow.document.open();
            iframe.contentWindow.document.write(data);
            iframe.contentWindow.document.close();
            $.unblockPage();
        },
        async: false
    });
}

$(document).ready(function () {
    showAlertMessages();
    loadIframeCentral();
});