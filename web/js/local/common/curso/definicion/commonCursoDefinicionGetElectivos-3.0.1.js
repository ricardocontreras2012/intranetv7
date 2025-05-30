
function addElectivo() {
    $("#electivos-form").attr("action", "CommonCursoDefinicionAddElectivo?key=" + $("#key").val()).attr("target", "_self").submit();
}

function modifyElectivo() {
    $("#electivos-form").attr("action", "CommonCursoDefinicionModifyElectivo?key=" + $("#key").val()).attr("target", "_self").submit();
}

function newElectivo()
{
    $('#asign option').eq(0).prop('selected', true);
    $('#minor option').eq(0).prop('selected', true);
    $("#elect").val('');
    $("#electividad").val('');
    $("#electivo-msg").html("");
    $("#new-modal").modal('show');
}

function deleteElectivos() {
    if (anyChecked("electivos-form") === true) {
        $("#confirmacion").modal('show');
    } else {
        $("#msg").modal('show');
    }
    return false;
}

function remove()
{
    $("#electivos-form").attr("action", "CommonCursoDefinicionRemoveElectivos?key=" + $("#key").val()).attr("target", "_self").submit();
    $("confirmacion").modal('hide');
}


function show(asign, elect, electivo, minor, area)
{
    $("#asignMod").val(asign);
    $("#electMod").val(elect);
    $("#minorMod").val(minor).change();
    $("#electivoMod").val(electivo);

    var valor = $('#areaMod option').filter(function () {
        return $(this).text() === area;
    }).val();

    $('#areaMod').val(valor);
    
    $("#modify-modal").modal('show');
}

$(document).ready(function () {
    $("#add-button").click(newElectivo);
    $("#delete-button").click(deleteElectivos);

    $('#save-new-electivo-event').on('click', function (evt) {
        addElectivo();
    });

    $('#save-modify-electivo-event').on('click', function (evt) {
        modifyElectivo();
    });

    $("#minorMod").change(function () {
        var txt = "";
        if ($("#minorMod option:selected").val() > 0)
        {
            txt = $("#minorMod option:selected").text();
            var nombre = txt.substring(txt.indexOf("::") + 2);
        }
        $("#electivoMod").val(nombre);
    });

    $("#electivos-table").dataTable({
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron resultados",
            "sInfo": "Mostrando desde _START_ hasta _END_ de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando desde 0 hasta 0 de 0 registros",
            "sInfoFiltered": "(filtrado de _MAX_ registros en total)",
            "sInfoPostFix": "",
            "sSearch": "Buscar:",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "Primero",
                "sPrevious": "Anterior",
                "sNext": "Siguiente",
                "sLast": "\u00daltimo"
            }
        },
        "aoColumns": [
            {"bSortable": false},
            {"sType": null},
            {"sType": null},
            {"sType": null}
        ],
        "aaSorting": [
            [0, "asc"]
        ], "aLengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
        "iDisplayLength": -1,
        "paging": false
    });
});
