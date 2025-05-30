
function getHorario(posValue) {
    var agno = $("#agno").val();
    var sem = $("#sem").val();

    $.get('CommonSalaGetHorario', {
        'key': $("#key").val(),
        'pos': posValue,
        'agno': agno,
        'sem': sem
    }, function (data) {
        $("#horario-div").html(data);
    });
}

function printHorario() {
    $("#salas-form").attr("action", "CommonSalaPrintHorario?key=" + $("#key").val() + "&agno=" + $("#agno").val() + "&sem=" + $("#sem").val() + '&contentDisposition=attachment; filename="sala_' + $("#sala").val() + '.pdf"');
    $("#salas-form").attr("method", "get");
    $("#salas-form").attr("target", "_blank");
    $("#salas-form").submit();
}

$(document).ready(function () {
    $.get('CommonSalaGetSalasPropias', {
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
});