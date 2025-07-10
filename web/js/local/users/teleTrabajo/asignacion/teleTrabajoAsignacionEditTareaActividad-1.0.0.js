function saveSolicitud() {
    const rowCount = $('#solicitud-table tr').length;
    if (rowCount > 0) {

        const data_string = $("#solicitud-form").serialize();
        jQuery.ajax({
            url: "TeleTrabajoAsignarSaveEditTarea",
            type: "POST",
            data: data_string,
            success: function (data) {
                //saveAttach();
                $("#solicitud-form").attr("action", "TeleTrabajoAsignarRefresh");
                $("#solicitud-form").submit();
            },
            async: false
        });

        return true;
    } else {
        return false;
    }
}

$(document).ready(function () {

    //Handlers
    $("#save-button").click(saveSolicitud);

});