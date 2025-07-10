function setSemanas() {

    let parts = $("#inicio").val().split("-");
    let dt = new Date(parseInt(parts[2], 10),
        parseInt(parts[1], 10) - 1,
        parseInt(parts[0], 10));
    $("#inicioPos").val(dt.getDay());
    
    if(dt.getDay() === 0){
        $("#inicioPos").val(7);
        dt.setDate(dt.getDate() - 7);
    }
    dt.setDate(dt.getDate() - dt.getDay() +1);

    let yyyy = dt.getFullYear();
    let mm = dt.getMonth() + 1;
    let dd = dt.getDate();

    if (dd < 10) dd = '0' + dd;
    if (mm < 10) mm = '0' + mm;
    
    $("#inicioSemana").val(dd + '-' + mm + '-' + yyyy);
    
    parts = $("#termino").val().split("-");
    dt = new Date(parseInt(parts[2], 10),
                  parseInt(parts[1], 10) - 1,
                  parseInt(parts[0], 10));
    $("#terminoPos").val(dt.getDay());
    
    if(dt.getDay() === 0){
        $("#terminoPos").val(7);
        dt.setDate(dt.getDate() - 7);
    }
    dt.setDate(dt.getDate() - dt.getDay() + 7);
    
    yyyy = dt.getFullYear();
    mm = dt.getMonth() + 1;
    dd = dt.getDate();
    
    if (dd < 10) dd = '0' + dd;
    if (mm < 10) mm = '0' + mm;
    
    $("#terminoSemana").val(dd + '-' + mm + '-' + yyyy);
}

function getHorario() {

    setSemanas();
    
    $.get('TeleTrabajoAsignarGetHorario', {
        'key': $("#key").val(),
        'inicio': $("#inicio").val(),
        'termino': $("#termino").val(),
        'inicioSemana': $("#inicioSemana").val(),
        'terminoSemana': $("#terminoSemana").val(),
        'inicioPos': $("#inicioPos").val(),
        'terminoPos': $("#terminoPos").val()
    }, function (data) {
        $("#horario-div").html(data);
        $("html, body").animate({scrollTop: $(document).height() - $(window).height()});
    });
}

function asignarActividad()
{
    $("#asignar").modal('hide');

    $("#actividad-form").attr("action", "TeleTrabajoSaveActividad");
    $("#actividad-form").attr("target", "_self");
    $("#actividad-form").submit();
}

function showAsignarActividad(celda) {
    const celdaAux = celda.substr(2);
    const pos = celdaAux.indexOf("_");
    const modulo = celdaAux.substr(0, pos);
    var dia = parseInt(celdaAux.substr(pos+1))+1;
    const fecha = $("table#horario thead tr th").eq(dia).html();
    const parts = fecha.split("/");
    const dt = new Date(parseInt(parts[2], 10),
        parseInt(parts[1], 10) - 1,
        parseInt(parts[0], 10));
    var dia = ["D","L","M","W","J","V","S"][dt.getDay()];
    
    $("#dia").val(dia);
    $("#modulo").val(modulo);
    $("#fecha").val(fecha);
    $("#title_actividad_div").html( $("#titulo").val()+" Fecha: "+fecha);
    
    $("#asignar").modal('show');
}

$(document).ready(function () {
    
    $("#buscar-button").click(getHorario);
    
    $("#asignar-button").click(asignarActividad);

    $("#actividad-form").validate({
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

    const today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());

    $('#inicio').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd-mm-yyyy',
        weekStartDay: 1,
        //minDate: today,
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
});