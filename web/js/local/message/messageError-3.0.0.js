

$(document).ready(function () {
    $("#msg-div").dialog({
        resizable: false,
        width: 300,
        modal: true,
        buttons: {
            "OK": function () {
                $(this).dialog("close");
            }
        },
        overlay: {
            opacity: 0.5,
            background: 'black'
        }
    });
});