
function addReport() {    
    $("#reportes-form").attr("action", "ProfesorReporteAddReporte").attr("target", "_self").submit();
}

function deleteReports() {
    if (anyChecked("reportes-form") === true) {
        $('#confirmacion').modal('show');
    } else {
        $('#msg').modal('show');
    }
    return false;
}

function viewReport(sesionVal) {
    $("#sesionReporte").val(sesionVal);
    $("#reportes-form").attr("action", "CommonReporteViewReporte").attr("target", "_self").submit();
}

function exportReports() {
    if (anyChecked("reportes-form") === true) {
        $("#reportes-form").attr("action", "CommonReporteExport").submit();

        return true;
    }

    $('#msg').modal('show');
    return false;
}

function remove()
{
    $("#reportes-form").attr("action", "ProfesorReporteRemoveReportes").attr("target", "_self").submit();

    return true;
}

$(document).ready(function () {
    //
    //Handlers
    $("#add-button").click(addReport);
    $("#export-button").click(exportReports);
    $("#delete-button").click(deleteReports);
    $("a").click(function () {
        var fieldName = $(this).attr("id");
        viewReport(fieldName.substr(fieldName.indexOf("_") + 1));
    });

    $("#msg").modal('hide');
    $("#confirmacion").modal('hide');

    $("#reportes-table").dataTable({
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
        "aoColumns": [{
                "bSortable": false
            }, {
                "sType": "numeric_link"
            }, {
                "sType": "uk_date_short"
            },
            null, null,
            {
                "sType": "uk_date_short"
            }],
        "aaSorting": [
            [1, "desc"]
        ]
    });
});