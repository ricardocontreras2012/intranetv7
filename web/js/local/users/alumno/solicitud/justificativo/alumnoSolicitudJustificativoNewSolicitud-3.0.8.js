function esFechaValida(fechaStr) {
    // Expresión regular para validar el formato dd-mm-yyyy
    const regex = /^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\d{4}$/;
    if (!regex.test(fechaStr)) {
        return false;
    }

    // Si pasa la expresión regular, convertimos la fecha a un objeto Date
    let  [day, month, year] = fechaStr.split('-').map(Number);
    const fecha = new Date(year, month - 1, day); // Mes - 1 porque en JS los meses empiezan desde 0

    // Verificamos que la fecha generada sea válida
    return fecha.getDate() === day && fecha.getMonth() + 1 === month && fecha.getFullYear() === year;
}

function saveSolicitud() {
    
    if ($("#eval").val() === "") {
        $("#aviso-eval-error").modal('show');
        return;
    }       
        
    const rowCount = $('#solicitud-table tr').length;
    // Validación de cantidad de filas
    if (rowCount <= 1) {
        $("#aviso-cursos-error").modal('show');
        return;
    }
    
    const selectedCourses = new Set();
    let hasDuplicate = false;

    $('#solicitud-table select[id^="curso_"]').each(function () {
        const value = $(this).val();    
        
        if (value !== "") {
            if (selectedCourses.has(value)) {
                hasDuplicate = true;
                return false; // corta el .each
            }
            selectedCourses.add(value);
        }
    });

    if (hasDuplicate) {
        // Puedes usar un modal o alert, como prefieras
        $("#aviso-duplicate-error").modal('show');
        return;
    }

    // Validación de fechas
    if (!checkDates()) {
        return;
    }

    // Validación de archivo
    if (!$("#upload").val()) {
        $("#aviso-archivo-error").modal('show');
        return;
    }

    // Envío de solicitud
    const data_string = $("#solicitud-form").serialize();
    jQuery.ajax({
        url: "AlumnoSolicitudSolicitudJustificativoPEPAdd",
        type: "POST",
        data: data_string,
        success: function (data) {
            saveAttach();
        },
        async: false
    });
}

function saveAttach()
{
    $("#attach-form").attr("action", "AlumnoSolicitudSolicitudJustificativoPEPAddAttach");
    $("#attach-form").submit();
}

function delRow(fila)
{
    $("table tr:eq(" + fila + ")").remove();
}

function getNewRowId()
{
    let ret = $("#id-row").val();
    ret++;
    $("#id-row").val(ret);
    return ret;
}

function addCurso()
{
    let id = getNewRowId();
    const lcSelect = '<select id="curso_' + id + '" name="curso_' + id + '" class="form-control">';
    const lcOption = $("#options-curso-div").html();
    const del = '<button id="delete-button" title="Curso" type="button" onClick="delRow(' + id + ')" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"></span></button>';
    const row = "<tr><td>" + lcSelect + lcOption + "</option></td><td>" + del + "</td></tr>";
    $('#solicitud-table > tbody:last-child').append(row);
    $("#id-row").val(id++);
}

function checkDates() {
    let valorInicio = $("#fechaInicio").val();
    let valorTermino = $("#fechaTermino").val();

    if (!valorInicio || !valorTermino) {
        $("#aviso-fechas-error .modal-body p").text("Por favor, complete ambas fechas.");
        $("#aviso-fechas-error").modal("show");
        return false;
    }

    if (!esFechaValida(valorInicio)) {
        alert("La fecha de inicio no es válida.");
        return false; // Detener el envío si la fecha no es válida
    }

    if (!esFechaValida(valorTermino)) {
        alert("La fecha de término no es válida.");
        return false; // Detener el envío si la fecha no es válida
    }

    const [dayInicio, monthInicio, yearInicio] = valorInicio.split('-');
    const [dayTermino, monthTermino, yearTermino] = valorTermino.split('-');

    const fechaInicioObj = new Date(yearInicio, monthInicio - 1, dayInicio);
    const fechaTerminoObj = new Date(yearTermino, monthTermino - 1, dayTermino);

    // Validar que la fecha de inicio no sea mayor que la de término
    if (fechaInicioObj > fechaTerminoObj) {
        alert("La fecha de inicio no puede ser mayor que la fecha de término.");
        return false; // Detener el envío si la fecha de inicio es mayor que la de término
    }

    return true;
}

$(document).ready(function () {
    //Handlers
    $("#save-button").click(saveSolicitud);
    $("#add-button").click(addCurso);

    $("#fechaInicio").datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd-mm-yyyy',
        weekStartDay: 1
    });

    $("#fechaTermino").datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd-mm-yyyy',
        weekStartDay: 1,
        minDate: function () {
            return $('#fechaInicio').val();
        }
    });
});
