

function printProgramas()
{
    if (anyChecked("calificacion-form") === true) {
        $("#calificacion-form").attr("action", "CommonAlumnoProgramaEstudioPrintProgramas").attr("target", "_blank").submit();

        return true;
    }
    $('#aviso').modal('show');
    return false;
}

$(document).ready(function () {

    $("#cartola-table").dataTable({
        "aLengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
        "iDisplayLength": 50,
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
            {
            "sType": null
        }, 
            {
            "sType": "asign_elect"
        }, {
            "sType": "string"
        }, {
            "sType": "string"
        }],
        "aaSorting": [
            [2, "asc"],
            [3, "asc"]
        ]
    });
});