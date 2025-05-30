
function addAlumno(pos) {
    $("#pos").val(pos);
    var dataString = $("#alumno-nomina-form").serialize();
    $('#message-iframe', window.parent.parent.document).attr("src", 'CommonAlumnoGetAlumno?' + dataString);  
    
    return false;
}

function addAyudante(pos) {
    $("#pos").val(pos);
    var dataString = $("#ayudante-nomina-form").serialize();
    $('#message-iframe', window.parent.parent.document).attr("src", 'CommonAyudanteGetAyudante?' + dataString);  
    
    return false;
}

function addProfesor(pos) {
    $("#pos").val(pos);
    var dataString = $("#profesor-nomina-form").serialize();
    
    $('#message-iframe', window.parent.parent.document).attr("src", 'CommonProfesorGetProfesor?' + dataString);  
    
    return false;
}

$(document).ready(function () {

    $("#search-table").dataTable({
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
                "sSortDataType": "html",
                "sType": "numeric_link"
            },
            null,
            null,
            null
        ]
    });
    //unblockPage();
});