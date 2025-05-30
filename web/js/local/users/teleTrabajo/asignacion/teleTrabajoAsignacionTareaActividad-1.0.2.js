
function saveTareas() {
    $("#tareas-form").attr("action", "TeleTrabajoSaveTarea").attr("target", "_self").submit();
}

function delRow(fila)
{
    $("#tarea-row_" + fila).remove();
}

function getNewRowId()
{
    var ret = $("#id-row").val();
    ret++;
    $("#id-row").val(ret);
    return ret;
}

function addTarea()
{
    var id = getNewRowId();
    var otro = '<input type="text" id="otro_' + id + '" name="otro_' + id + '" class="form-control" maxlength="200"/>';
    var del = '<button id="delete-button" title="Eliminar" type="button" onClick="delRow(' + id + ')" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"></span></button>';
    var row = "<tr id=\"tarea-row_" + id + "\"><td>" + otro + "</td><td>" + del + "</td></tr>";
    $('#tareas-table > tbody:last-child').append(row);
    $("#id-row").val(id);
}

$(document).ready(function () {
    $("#save-button").click(saveTareas);
    $("#add-button").click(addTarea);
});