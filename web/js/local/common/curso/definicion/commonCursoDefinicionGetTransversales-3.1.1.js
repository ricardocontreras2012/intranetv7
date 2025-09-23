
function setTransversales() {
    Swal.fire({
        html: `
        <div style="display: flex; align-items: center;">
            <div class="spinner-border text-primary" role="status" style="width: 2rem; height: 2rem; margin-right: 1rem;">
                <span class="sr-only">Cargando...</span>
            </div>
            <div>
                <h5 style="margin-bottom: 0.25rem;">Guardando información</h5>
                <small style="color: #6c757d;">Por favor, espere un momento...</small>
            </div>
        </div>
    `,
        allowOutsideClick: false,
        allowEscapeKey: false,
        showConfirmButton: false,
        backdrop: true,
        customClass: {
            popup: 'swal2-popup-inline-style'
        },
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



