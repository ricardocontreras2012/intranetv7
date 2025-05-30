

$(document).ready(function () {


    $("#msg-div").dialog({
        resizable: false,
        width: 300,
        modal: true,
        buttons: {
            "OK": function () {
                window.close();
            }
        }
    });
});


