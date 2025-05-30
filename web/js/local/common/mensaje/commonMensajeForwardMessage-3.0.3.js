function sendForwardMessage() {
    $.blockUI();
    $("#message-fwd-form").attr("action", "CommonMensajeSendFwdMessage").attr("method", "post").attr("target", "_self").submit();
}

$(document).ready(function () {
    jQuery("textarea").each(function () {
        $(this).val(jQuery.trim($(this).val()));
    });
    //unblockPage();
});