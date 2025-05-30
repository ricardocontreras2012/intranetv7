
function save() {
    $("#solicitud-form").attr("action", "CommonPracticaSaveResolucionPractica").attr("target", "_self").submit();
}

$(document).ready(function () {
    //Handlers
    $("#save-button").click(save);
    jQuery("textarea").each(function () {
        $(this).val(jQuery.trim($(this).val()));
    });

    $("#estado option[value='" + $("#status").val() + "']").prop("selected", true);
});