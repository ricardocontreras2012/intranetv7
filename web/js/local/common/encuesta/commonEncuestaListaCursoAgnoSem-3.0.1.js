

function showEncuesta(posVal) {
    $("#pos").val(posVal);
    $("#cursos-form").attr("action", "CommonProfesorGetEncuesta").submit();
}

function printEncuestas() {
    if (anyChecked("cursos-form") === true) {
        $("#flag").val("S");
        callActionEncuesta();
    }
    else {
        $("#msg-div").dialog("open");
    }
    return false;
}

function searchCursos()
{
    $("#cursos-form").attr("action", "JefeAreaGetCursosEncuesta");
    $("#cursos-form").attr("target", "_self");
    $("#cursos-form").submit();
}

function printAll() {
    $("#flag").val("F");
    callActionEncuesta();
}

function callActionEncuesta() {
    $("#cursos-form").attr("action", "CommonEncuestaPrintGroupCursos");
    $("#cursos-form").attr("target", "_blank");
    $("#cursos-form").submit();
}

$(document).ready(function () {
    //Handlers
    $("#print-button").click(printEncuestas);
    $("#all-button").click(printAll);
    $("#search-button").click(searchCursos);
    $("a").click(function () {
        var field_name = $(this).attr("id");
        showEncuesta(field_name.substr(field_name.indexOf("_") + 1));
    });
    //
    $("#cursos-table").dataTable({"aLengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
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
                "sType": null
            },
            {
                "sType": null
            },
            {
                "sType": null
            }
        ],
        "aaSorting": [
            [ 1, "asc" ]
        ],
        "iDisplayLength": -1
    });
});

