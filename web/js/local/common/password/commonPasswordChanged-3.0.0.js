
$(document).ready(function () {
    $("#msg-div").dialog({
        resizable: false,
        width: 200,
        buttons: {
            "OK": function () {
                $(this).dialog("close");
            }
        }
    });
});