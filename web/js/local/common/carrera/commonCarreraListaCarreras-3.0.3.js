
function executeActionDummy() {
    $("#carreras-form").attr("action", $("#actionDummy").val());
    $("#carreras-form").attr("target", "_self").submit();
}

function generaActas()
{
    let flag = "";
    if ($("#ck_r").prop('checked'))
    {
        flag ="R";
    }
    if ($("#ck_b").prop('checked'))
    {
        flag +="B";
    }
    if ($("#ck_i").prop('checked'))
    {
        flag +="I";
    }
    if ($("#ck_m").prop('checked'))
    {
        flag +="M";
    }

    $("#flag").val(flag);

    executeActionDummy();
}

function enableActas()
{
    $("#conf-emision-actas").modal('show');
}

$(document).ready(function () {
    $("#execute-button").click(enableActas);

    $("#carreras-table").dataTable({
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
            }
        ],
        "aaSorting": [
            [ 0, "asc" ]
        ],
        "iDisplayLength": 20
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