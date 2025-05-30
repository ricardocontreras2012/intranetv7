

function buscarInscripcion() {
    $("#inscripcion-form").attr("action", "CommonRequisitoAdicionalLogroGetInscripciones");
    $("#inscripcion-form").attr("target", "_self");
    $("#inscripcion-form").submit();
}

function buscarAlumno() {
    $("#inscripcion-form").attr("action", "CommonAlumnoSearchEnable?actionCall=CommonRequisitoAdicionalLogroAddAlumno&typeSearch=F");
    $("#inscripcion-form").attr("target", "_self");
    $("#inscripcion-form").submit();
}

function inscribir() {
    $("#inscripcion-form").attr("action", "CommonRequisitoAdicionalLogroInscribir");
    $("#inscripcion-form").attr("target", "_self");
    $("#inscripcion-form").submit();
}

function deleteInscripciones() {
    if (anyChecked("inscripcion-form") === true) {
        $("#confirmacion").dialog("open");
    }
    else {
        $("#msg-div").dialog("open");
    }
    return false;
}

function viewGlosa(posVal) {
    $("#pos").val(posVal);
    $("#inscripcion-form").attr("action", "CommonRequisitoAdicionalLogroGetInscripcion");
    $("#inscripcion-form").attr("target", "_self");
    $("#inscripcion-form").submit();
}


$(document).ready(function () {


    //Handlers
    $("#add-button").click(buscarAlumno);
    $("#delete-button").click(deleteInscripciones);
    $("#trequisitoLogroAdicional").change(buscarInscripcion);

    $("a").click(function () {
        var field_name = $(this).attr("id");
        viewGlosa(field_name.substr(field_name.indexOf("_") + 1));
    });
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
            }
            ,
            {
                "sType": "html"
            }
        ],
        "aaSorting": [
            [0, 'asc'],
            [1, 'asc']
        ],
        "iDisplayLength": 20
    });

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
                $("#inscripcion-form").attr("action", "CommonRequisitoAdicionalLogroRemoveInscripciones");
                $("#inscripcion-form").attr("target", "_self");
                $("#inscripcion-form").submit();
                $(this).dialog("close");
            }
        }
    });

    $("#msg-div").dialog({
        autoOpen: false,
        resizable: false,
        modal: true,
        width: 300,
        buttons: {
            "OK": function () {
                $(this).dialog("close");
            }
        }
    });

    $("#trequisitoLogroAdicional option[value='" + $("#tipoDummy").val() + "']").prop("selected", true);

    $("#msgError").dialog({
        autoOpen: false,
        resizable: false,
        modal: true,
        width: 300,
        buttons: {
            "OK": function () {
                $(this).dialog("close");
            }
        }
    });

    if ($("#msgError").text() !== '') {
        $("#msgError").dialog("open");
    }
});