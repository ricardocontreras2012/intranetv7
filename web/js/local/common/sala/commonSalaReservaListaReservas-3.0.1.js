
function addReserva() {
    $("#reservas-form").attr("action", "CommonSalaReservaEnable");
    $("#reservas-form").attr("target", "_self");
    $("#reservas-form").submit();
}

function remove()
{
    $("#reservas-form").attr("action", "CommonSalaReservaRemoveReserva");
    $("#reservas-form").attr("target", "_self");
    $("#reservas-form").submit();
}

function deleteReservas() {
    if (anyChecked("reservas-form") === true) {
        $("#confirmacion").modal('show');
    } else {
        $("#aviso").modal('show');
    }
    return false;
}

$(document).ready(function () {
    //Handlers
    $("#add-button").click(addReserva);
    $("#delete-button").click(deleteReservas);
    //
    $("#aviso").modal('hide');
    $("#confirmacion").modal('hide');

    $("#reservas-table").dataTable({
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
                "bSortable": false
            },
            {
                "bSortable": true
            },
            {
                "bSortable": true
            },
            {
                "bSortable": true
            },
            {
                "sType": "uk_date_short"
            },
            {
                "sType": "uk_date_short"
            },
            {
                "bSortable": false
            }
        ],
        "aaSorting": [
            [1, "desc"]
        ]
    });
});