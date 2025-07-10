
function showCurso(curso)
{
    $.get('CommonSalaReservaGetCurso', {
        'key': $("#key").val(),
        'curso': curso
    }, function (data) {
        $("#detail-div").html(data);
        $("#detail").modal('show');
    });
}

function showReserva(reserva)
{
    $.get('CommonSalaReservaGetReserva', {
        'key': $("#key").val(),
        'reserva': reserva
    }, function (data) {
        $("#detail-div").html(data);
        $("#detail").modal('show');
    });
}

function getContrastYIQ(hex) {
    // Elimina el "#" si está presente
    hex = hex.replace(/^#/, "");

    // Valida que tenga 6 caracteres
    if (hex.length !== 6) {
        console.warn("Color hexadecimal inválido:", hex);
        return "black"; // Valor por defecto
    }

    const r = parseInt(hex.slice(0, 2), 16);
    const g = parseInt(hex.slice(2, 4), 16);
    const b = parseInt(hex.slice(4, 6), 16);

    const yiq = (r * 299 + g * 587 + b * 114) / 1000;
    return yiq >= 128 ? "black" : "white";
}


$(document).ready(function () {
    //$("#print-button").click(printHorario);

    $('#horario td').on('click').click(function () {
        if ($.trim($(this).text().replace(/[\s\n\r]+/g, ' ')) === '')
        {
            showReservar($(this).attr("id"));
        }
    });
});