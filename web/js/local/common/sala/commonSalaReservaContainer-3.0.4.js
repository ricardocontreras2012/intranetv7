
function getHorario(posValue) {
    
    $.get('CommonSalaReservaGetHorario', {
        'key': $("#key").val(),
        'pos': posValue,
        'inicio': $("#inicio").val(),
        'termino': $("#termino").val()
    }, function (data) {
        $("#horario-div").html(data);
        $("html, body").animate({scrollTop: $(document).height() - $(window).height()});
    });
}

function reservar()
{
    $("#reserva").modal('hide');
    $("#inicio").val($("#inicioReserva").val());
    $("#termino").val($("#terminoReserva").val());

    $("#salas-form").attr("action", "CommonSalaReservaReservar");
    $("#salas-form").attr("target", "_self");
    $("#salas-form").submit();
}

function showReservar(celda) {
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
    //$("#modulo-horario").html($("#h_" + modulo).text());
    $("#inicioReserva").val($("#inicio").val());
    $("#terminoReserva").val($("#termino").val());    
    $("#title_sala_div").html( $("#title_sala_div").html()+" Módulo:"+$("#dia").val()+(parseInt($("#modulo").val())+1));
    
    
    $("#reserva").modal('show');
}

$(document).ready(function () {
    $("#reservar-button").click(reservar);

    $.get('CommonSalaReservaGetSalas', {
        'key': $("#key").val()
    }, function (data) {
        $("#lista-salas-div").html(data);
    });

    $("#salas-form").validate({
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
        minDate: today,
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

    $('#inicioReserva').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd-mm-yyyy',
        weekStartDay: 1,
        minDate: today,
        maxDate: function () {
            return $('#terminoReserva').val();
        }
    });

    $('#terminoReserva').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        locale: 'es-es',
        format: 'dd-mm-yyyy',
        weekStartDay: 1,
        minDate: function () {
            return $('#inicioReserva').val();
        }
    });
});