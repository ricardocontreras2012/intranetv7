

function addEspejo()
{
  $("#new-modal").modal('show');
}

function deleteEspejos()
{
    $("#confirmacion").modal('show');
}

function remove()
{
    $("#cursos-form").attr("action", "CommonCursoDefinicionRemoveEspejos").attr("target", "_self").submit();
}

function save()
{
    $("#new-modal").modal('hide');
    $("#cursos-form").attr("action", "CommonCursoDefinicionSaveEspejo").attr("target", "_self").submit();
}

$(document).ready(function () {
    $("#add-button").click(addEspejo);
    $("#delete-button").click(deleteEspejos);

     $('#save-new-espejo-event').on('click', function (evt) {
        save();
    });

    $("#cursos-table").dataTable({
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
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
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



