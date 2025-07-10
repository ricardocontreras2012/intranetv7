function executeAction(posValue) {
    $("#pos").val(posValue);
    $("#alumnoBoletin-form").attr("action", "CommonCursoGetBoletinHorario");
    $("#alumnoBoletin-form").submit();
}

$(document).ready(function () {
    $("a").click(function () {
        const field_name = $(this).attr("id");
        executeAction(field_name.substr(field_name.indexOf("_") + 1));
    });
    
    $("#alumnosBoletin-table").DataTable({
        language: {
            info: 'Mostrando página _PAGE_ de _PAGES_',
            infoEmpty: 'Mostrando desde 0 hasta 0 de 0 registros',
            infoFiltered: '(filtrado de _MAX_ registros en total)',
            lengthMenu: 'Mostrar _MENU_ registros por página',
            zeroRecords: 'No se encontraron resultados',
            processing: 'Procesando...',
            search: "Buscar:",
            "paginate": {
                "first": "Primero",
                "last": "\u00daltimo",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        },
        order: [[0, 'asc']]
    });
    
    
});