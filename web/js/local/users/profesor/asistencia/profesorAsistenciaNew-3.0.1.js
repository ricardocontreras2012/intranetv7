

function saveLista() {
    $("#lista-form").attr("action", "CommonAsistenciaSaveNewAsistencia?key=" + $("#keyDummy").val());
    $("#lista-form").attr("target", "_self");
    $("#lista-form").submit();
}

$(document).ready(function () {

    //Handlers
    $("#save-button").click(saveLista);


    $("#lista-form").validate({
        event: "blur",
        rules: {
            fechaAsistencia: {
                required: true,
            }
        },
        messages: {
            fechaAsistencia: {
                required: jQuery.validator.messages.required
            }
        }
    });

    var today;
    today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
    $('#fechaAsistencia').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd/mm/yyyy',
        weekStartDay: 1,
        maxDate: today
    });
});