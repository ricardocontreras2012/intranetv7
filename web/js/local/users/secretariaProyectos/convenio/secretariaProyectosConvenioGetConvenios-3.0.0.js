function nuevo()
{
    $("#convenios-form").attr("action", "SecretariaProyectosConvenioNewConvenio").attr("target", "_self").submit();
}

function remove()
{
    $("#convenios-form").attr("action", "SecretariaProyectosConvenioRemoveConvenios").attr("target", "_self").submit();
    $("#confirmacion").modal('hide');
}

function confirmar()
{
    $("#confirmacion").modal('show');
}

function getConvenio(pos)
{
    $("#pos").val(pos);
    $("#convenios-form").attr("action", "SecretariaProyectosConvenioShowConvenio").attr("target", "_self").submit();
}

$(document).ready(function () {

    $("#add-button").click(nuevo);
    $("#delete-button").click(confirmar);
 
    $("a").click(function () {
        var field_name = $(this).attr("id");
        getConvenio(field_name.substr(field_name.indexOf("_") + 1));
    });

    $("#convenios-table").dataTable({
        "autoWidth": false,
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
            {"sType": null, "width": "2%"},
            {"sType": "number", "width": "5%"},
            {"sType": "string", "width": "30%"},
            {"sType": "string", "width": "15%"},
            {"sType": "string", "width": "25%"},
            {"sType": "string", "width": "23%"}
        ],
        "aLengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
        "iDisplayLength": -1
    });
});
