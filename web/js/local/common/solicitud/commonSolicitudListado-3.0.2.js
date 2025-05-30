function onLoad()
{
    $("#estadoList option[value='" + $("#estado").val() + "']").prop("selected", true);
}

function viewSolicitud(pos) {
    $("#pos").val(pos);
    $("#solicitudes-form").attr("action", "CommonSolicitudViewSolicitud");
    $("#solicitudes-form").attr("target", "_self");
    $("#solicitudes-form").submit();
}

function verSolicitudes() {
    blockPage();
    var e = document.getElementById("estadoList");
    var value = e.options[e.selectedIndex].value;
    $("#estado").val(value);
    $("#solicitudes-form").attr("action", "CommonSolicitudGetSolicitudes");
    $("#solicitudes-form").attr("target", "_self");
    $("#solicitudes-form").submit();
}

function reloadPage()
{
    $("#solicitudes-form").attr("action", "CommonSolicitudGetSolicitudes");
    $("#solicitudes-form").submit();
}

$(document).ready(function () {
    //Handlers
    $("a").click(function () {
        var fieldName = $(this).attr("id");
        viewSolicitud(fieldName.substr(fieldName.indexOf("_") + 1));
    });
    
    $("#reload-button").click(reloadPage);
    
    $('#inicio').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd-mm-yyyy',
        weekStartDay: 1,
        maxDate: function () {
            return $('#termino').val();
        }
    });

    $('#termino').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd-mm-yyyy',
        weekStartDay: 1,
        minDate: function () {
            return $('#inicio').val();
        }
    });
    
    $("#solicitudes-table").DataTable({
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
        order: [[3, 'desc']]
    });
    
    unblockPage();
});
