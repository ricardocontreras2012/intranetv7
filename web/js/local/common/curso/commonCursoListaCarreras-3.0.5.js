function executeAction(posValue) {

    Swal.fire({
        html: `
        <div style="display: flex; align-items: center;">
            <div class="spinner-border text-primary" role="status" style="width: 2rem; height: 2rem; margin-right: 1rem;">
                <span class="sr-only">Cargando...</span>
            </div>
            <div>
                <h5 style="margin-bottom: 0.25rem;">Cardando información</h5>
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
            $("#carreras-form").attr("action", $("#actionName").val() + "?pos=" + posValue + '&key=' + $("#keyDummy").val());
            $("#carreras-form").attr("target", "_self").submit();
        }
    });
}

$(document).ready(function () {
    //Handler
    $("a").click(function () {
        const field_name = $(this).attr("id");
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
            [0, "asc"]
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