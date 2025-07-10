
$("a").click(function () {
    const field_name = $(this).attr("id");
    getHorario(field_name.substr(field_name.indexOf("_") + 1));
});

$("#salas-table").dataTable({
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
    "sPaginationType": "full_numbers",
    "aoColumns": [
        {
            "sSortDataType": "html",
            "sType": "numeric_link"
        },

        {
            "sType": null
        },
        {
            "sType": null
        },
        {
            "sType": null
        }
    ],
    "iDisplayLength": 5
});