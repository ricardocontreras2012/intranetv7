
function setTransversales() {
    Swal.fire({
        title: 'Guardando...',
        html: '<div class="spinner-border text-primary" role="status"></div><br><br>Por favor, espera.',
        allowOutsideClick: false,
        allowEscapeKey: false,
        showConfirmButton: false,
        backdrop: true,
        didOpen: () => {
            $("#cursos-form")
                .attr("action", "CommonCursoDefinicionSaveTransversales")
                .attr("target", "_self")
                .submit();
        }
    });
}

$(document).ready(function () {    
    if (Swal.isVisible()) {
        Swal.close();
    }
    
    $("#save-button").click(setTransversales);

    $("#cursos-table").dataTable({
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
            {"bSortable": false},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null},
            {"sType": null}
        ],
        "aaSorting": [
            [0, "asc"]
        ], "aLengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
        "iDisplayLength": -1,
        "paging": false
    });

     $("table").on("click", ":checkbox", function ()
    {
        if ($(this).is(':checked'))
        {
            $(this).parents("tr:first").data('prevColor', $(this).parents("tr:first").css('background-color'));
            $(this).parents("tr:first").css('background-color', '#93E9BE');

        } else
        {
            $(this).parents("tr:first").css('background-color', '#F0FAFF');
        }
    });

});



