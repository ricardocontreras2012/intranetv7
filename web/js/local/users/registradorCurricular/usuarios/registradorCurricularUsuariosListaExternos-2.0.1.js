

function addUser() {
    $("#users-form").attr("action", "ProfesorReporteAddReporte");
    $("#users-form").attr("target", "_self");
    $("#users-form").submit();
}

function deleteUsers() {
    if (anyChecked("users-form") === true) {
        $("#confirmacion").dialog("open");
    }
    else {
        $("#msg-div").dialog("open");
    }
    return false;
}

$("#confirmacion").dialog({
    autoOpen: false,
    resizable: false,
    modal: true,
    width: 400,
    buttons: {
        "NO": function () {
            $(this).dialog("close");
        },
        "SI": function () {
            $("#users-form").attr("action", "RegistradorCurricularUserExternoRemove");
            $("#users-form").attr("target", "_self");
            $("#users-form").submit();
            $(this).dialog("close");
        }
    }
});

$("#msg-div").dialog({
    autoOpen: false,
    resizable: false,
    width: 300,
    buttons: {
        "OK": function () {
            $(this).dialog("close");
        }
    }
});

$(document).ready(function () {


    $("#add-button").click(addUser);
    $("#delete-button").click(deleteUsers);
    //Handlers

    $("#users-table").dataTable({
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
                "sSortDataType": "html",
                "sType": "numeric_link"
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
        "iDisplayLength": 20
    });
});