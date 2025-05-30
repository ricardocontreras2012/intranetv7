
function saveSolicitud() {
    var rowCount = $('#solicitud-table tr').length;
    if (rowCount > 0) {

        var data_string = $("#solicitud-form").serialize();
        jQuery.ajax({
            url: "AlumnoSolicitudAddSolicitudInscripcion",
            type: "POST",
            data: data_string,
            success: function (data) {
                saveAttach();
            },
            async: false
        });


        return true;
    } else {
        return false;
    }
}

function saveAttach()
{    
    $("#attach-form").attr("action", "AlumnoSolicitudAddSolicitudInscripcionAttach");
    $("#attach-form").submit();
}

function delRow(fila)
{
    $("table tr:eq(" + fila + ")").remove();
}

function getNewRowId()
{
    var ret = $("#id-row").val();
    ret++;
    $("#id-row").val(ret);
    return ret;
}

function addCurso()
{
    var id = getNewRowId();
    var lcSelect = '<select id="curso_' + id + '" name="curso_' + id + '" class="form-control">';
    var lcOption = $("#options-curso-div").html();
    var lmSelect = '<select id="motivo_' + id + '" name="motivo_' + id + '" class="form-control" onChange="verMotivo(' + id + ')">';
    var lmOption = $("#options-motivo-div").html();
    var otro = '<input type="text" id="otro_' + id + '" name="otro_' + id + '" class="form-control" readonly maxlength="200"/>';
    var del = '<button id="delete-button" title="Curso" type="button" onClick="delRow(' + id + ')" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"></span></button>';
    var row = "<tr><td>" + lcSelect + lcOption + "</option></td><td>" + lmSelect + lmOption + "</td><td>" + otro + "</td><td>" + del + "</td></tr>";
    $('#solicitud-table > tbody:last-child').append(row);
    $("#id-row").val(id++);
}

function verMotivo(fila)
{
    var opcion = $("#motivo_" + fila).val();
    $("#otro_" + fila).prop("readonly", true);
    $("#otro_" + fila).val('');
    if (opcion === "12")
    {
        $("#otro_" + fila).prop("readonly", false);
    }
}

$(document).ready(function () {

    //Handlers
    $("#save-button").click(saveSolicitud);
    $("#add-button").click(addCurso);

    /*$("#attach").uploadFile({
     multiple: true,
     dragDrop: true,
     fileName: "myfile"
     });*/


   // $('#upload').customFile();

});