function showEvidencia(pos) {
    $("#pos").val(pos);
    var dataString = $("#tareas-form").serialize();
    $('#main-content-iframe', window.parent.document).attr("src", 'TeleTrabajoGetMiEvidencia?'+ dataString);
}

function deleteTarea() {
    if (anyChecked("tareas-form") === true) {
        $("#confirmacion").modal('show');
    } else {
        $("#aviso").modal('show');
    }
    return false;
}

function removeTareas() {
    $("#tareas-form").attr("action", "TeleTrabajoRemoveTarea").submit();    
}

function editTarea()
{
    $("#tareas-form").attr("action", "TeleTrabajoEditTarea").submit();
}

function newTarea()
{
    $("#tareas-form").attr("action", "TeleTrabajoAddTarea").submit();
}

$(document).ready(function () {
    $("#add-button").click(newTarea);
    $("#edit-button").click(editTarea);
    $("#delete-button").click(deleteTarea);
    $("a").click(function () {
        var fieldName = $(this).attr("id");
        showEvidencia(fieldName.substr(fieldName.indexOf("_") + 1));
    });
});