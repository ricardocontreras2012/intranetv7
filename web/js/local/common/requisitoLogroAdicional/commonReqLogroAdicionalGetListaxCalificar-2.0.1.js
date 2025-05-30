

function buscarInscripcion() {
    $("#acta-form").attr("action", "CommonRequisitoAdicionalLogroGetListaxCalificar");
    $("#acta-form").attr("target", "_self");
    $("#acta-form").submit();
}

function emitirActa() {
    if ($("#acta-form").valid()) {
        $("#confirmacion").dialog("open");
    }
    return false;
}

$(document).ready(function () {


    //Handlers
    $("#emitir-button").click(emitirActa);
    $("#trequisitoLogroAdicional").change(buscarInscripcion);

    //
    $("#inscripciones-table").dataTable({
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
                "sType": null
            },
            {
                "sType": null
            },
            {
                "sType": null
            }
            ,
            {
                "sType": null
            }
            ,
            {
                "sType": null
            }
            ,
            {
                "sType": null
            }
        ],
        "aaSorting": [
            [0, 'asc'],
            [1, 'asc']
        ],
        "iDisplayLength": 20
    });

    $("#trequisitoLogroAdicional option[value='" + $("#tipoDummy").val() + "']").prop("selected", true);

    $("#acta-form :input").keydown(function (e) {
        if (enterKey(e)) {
            $("#acta-form").valid();
            var fields = $(this).parents('form:eq(0),body').find('button,input,textarea,select');
            var index = fields.index(this);
            if (index > -1 && (index + 1) < fields.length) {
                fields.eq(index + 1).focus();
            }
        }
    });

    //
    $("#acta-form").validate({
        validationEventTriggers: "blur",
        success: function (label) {
            var id = label.attr("for");
            var input = $("#" + id);
            var value = input.val();

            (value < 4) ? input.attr("class", "reprobado") : input.attr("class", "aprobado");
        },
        event: "blur",
        rules: {},
        messages: {}
    });

    $("#acta-form :input").each(function () {
        var field_name = $(this).attr("id");
        if (field_name !== undefined && field_name !== null) {
            if (field_name.startsWith("nota_"))
            {
                $(this).rules("add", {
                    formatoNota: true
                });
            }
            if (field_name.startsWith("fecha_"))
            {
                $(this).rules("add", {
                    formatoFecha: true
                });
            }
        }
    });

    $("#confirmacion").dialog({
        autoOpen: false,
        resizable: false,
        modal: true,
        width: 300,
        buttons: {
            "NO": function () {
                $(this).dialog("close");
            },
            "SI": function () {
                $("#acta-form").attr("action", "CommonRequisitoAdicionalLogroEmitirActa");
                $("#acta-form").attr("onsubmit", "return true;");
                $("#acta-form").submit();

                $(this).dialog("close");
            }
        }
    });

});