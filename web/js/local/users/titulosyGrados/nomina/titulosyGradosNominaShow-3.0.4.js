

function expedienteAction(action)
{
    $("#tipo").val($("select#tipo-select option").filter(":selected").val());
    $("#nomina-form").attr("action", action).attr("method", "POST").attr("target", "_self").submit();

    return false;
}

function showAviso() {
    $("#aviso").modal('show');
}

function showConfirmacion() {
    $("#confirmacion").modal('show');
}

function searchNomina()
{
    if ($("#nomina-form").valid())
    {
        expedienteAction("TitulosyGradosNominaGetNomina");
    }
}


function printNominaRector()
{
    if ($("#nomina-form").valid())
    {
        expedienteAction("TitulosyGradosNominaPrintNominaRector");
    }
}

function printResolucion()
{
    if ($("#nomina-form").valid())
    {
        expedienteAction("TitulosyGradosNominaResolucionPrint");
    }
}

function remove()
{
    if ($("#nomina-form").valid())
    {
        expedienteAction("TitulosyGradosNominaRemove");
    }
}

function deleteAlumno() {
    if (anyChecked("nomina-form") === true) {
        showConfirmacion();
    }
    else
    {
        showAviso();
    }
    return false;
}

$(document).ready(function () {

/*
    $("#nomina-table").dataTable({
        "aLengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
        "iDisplayLength": 25,
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
            },
            {
                "sType": null, "bSortable": false
            },
            {
                "sType": "uk_date_short", "bSortable": false
            },
            {
                "sType": null, "bSortable": false
            }
        ],
        "aaSorting": [[3, "asc"]]
    });

    */

    $("#search-button").click(searchNomina);

    if ($("#tipo").val() !== '') {
        $("#tipo-select option[value='" + $("#tipo").val() + "']").prop("selected", true);
    }

    $("#nomina-form").validate({
        event: "blur",
        rules: {
            agno: {required: true, number: true},
            nomina: {required: true, number: true}
        },
        messages: {
            agno: {required: jQuery.validator.messages.required, number: jQuery.validator.messages.digits},
            nomina: {required: jQuery.validator.messages.required, number: jQuery.validator.messages.digits}
        }
    });

    $("#nomina-form :input").each(function () {
        var fieldName = $(this).attr("id");

        if (fieldName !== undefined && fieldName !== null)
        {
            if (fieldName.startsWith("fe_"))
            {
                $(this).mask("99/99/9999");
            }

            if (fieldName.startsWith("ne_")) {
                $(this).rules("add", {
                    number: true,
                    messages: {number: jQuery.validator.messages.digits}
                });
            }

            if (fieldName.startsWith("rol_")) {
                $(this).rules("add", {
                    required: true,
                    messages: {required: jQuery.validator.messages.required}
                });
            }
        }
    });

});
