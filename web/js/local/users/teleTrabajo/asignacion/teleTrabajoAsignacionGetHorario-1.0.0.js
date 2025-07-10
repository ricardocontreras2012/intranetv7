function showActividad(fecha)
{
    
}

function getContrastYIQ(hexcolor){
    const r = parseInt(hexcolor.substr(0, 2), 16);
    const g = parseInt(hexcolor.substr(2, 2), 16);
    const b = parseInt(hexcolor.substr(4, 2), 16);
    const yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000;
    return (yiq >= 128) ? 'black' : 'white';
}

function ChangeBackgroundColor(panel){
    panel.style.backgroundColor = "#9999ff";
}

function RestoreBackgroundColor(panel){
    panel.style.backgroundColor = "#ffffff"; 
}

$(document).ready(function () {
    $('#horario td.asignar').on('click').click(function () {
        if ($.trim($(this).text().replace(/[\s\n\r]+/g, ' ')) === '')
        {
            showAsignarActividad($(this).attr("id"));
        }
    });

});