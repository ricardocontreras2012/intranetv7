
function searchCursos()
{
    $("#cursos-form").attr("action", "JefeAreaMaterialesGetResumen").attr("target", "_self").submit();
}

function getMateriales(pos)
{
    $("#cursos-form").attr("action", "CommonCursoGetCursoResumen?pos="+pos+"&actionCall=CommonMaterialConsultaMateriales");
    $("#cursos-form").attr("target", "_self").submit();
}

$(document).ready(function () {
    $("a").click(function () {
        var field_name = $(this).attr("id");
        getMateriales(field_name.substr(field_name.indexOf("_") + 1));
    });

    //Handlers
    $("#search-button").click(searchCursos);
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
            [1, "asc"]
        ],
        "iDisplayLength": -1
    });
});

