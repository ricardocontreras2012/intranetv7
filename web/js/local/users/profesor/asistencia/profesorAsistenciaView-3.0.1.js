

function saveLista() {
    $("#lista-form").attr("action", "CommonAsistenciaSaveModifiedAsistencia");
    $("#lista-form").attr("target", "_self");
    $("#lista-form").submit();
}

function remove()
{
    $("#lista-form").attr("action", "CommonAsistenciaRemoveAsistencia");
    $("#lista-form").attr("target", "_self");
    $("#lista-form").submit();
}

function deleteAsistencia() {
    $("#confirmacion").dialog("open");
    return false;
}

$(document).ready(function () {

    //Handlers
    $("#save-button").click(saveLista);
    $("#delete-button").click(deleteAsistencia);

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

    let today;
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