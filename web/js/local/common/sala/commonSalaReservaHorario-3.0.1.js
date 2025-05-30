
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
function getContrastYIQ(hexcolor){
	var r = parseInt(hexcolor.substr(0,2),16);
	var g = parseInt(hexcolor.substr(2,2),16);
	var b = parseInt(hexcolor.substr(4,2),16);
	var yiq = ((r*299)+(g*587)+(b*114))/1000;
	return (yiq >= 128) ? 'black' : 'white';
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