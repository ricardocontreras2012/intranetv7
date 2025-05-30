function executeAction(posValue) {
    $("#carreras-form").attr("action", $("#actionName").val() + "?pos=" + posValue + '&key=' + $("#keyDummy").val());
    $("#carreras-form").attr("target", "_blank").submit();
}

$(document).ready(function () {
    //Handler
    $("a").click(function () {
        var field_name = $(this).attr("id");
        executeAction(field_name.substr(field_name.indexOf("_") + 1));
    });

    $("#carreras-table").dataTable({
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
                "sType": null
            }
        ],
        "aaSorting": [
            [ 0, "asc" ]
        ],
        "iDisplayLength": 18
    });

    $("#carreras-form").validate({
        event: "blur",
        rules: {
            agno: {
                required: true,
                min: 1900
            },
            sem: {
                required: true,
                min: 1,
                max: 3
            }
        }
    });
});