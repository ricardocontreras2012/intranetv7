
function showNotas(asignatura, nombre, electiva) {
    $("#asignatura").val(asignatura);
    $("#nombre").val(nombre);
    $("#electiva").val(electiva);
    var data_string = $("#malla-form").serialize();
    jQuery.ajax({
        url: "CommonAlumnoGetNotasAsignatura",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#notas-div").html(data);
        },
        async: false
    });
    
    $('#modal-notas').modal('show');
    return false;
}

function showAdicionales(adicional, nombre) {
    $("#adicional").val(adicional);
    $("#nombre").val(nombre);
    var data_string = $("#malla-form").serialize();
    jQuery.ajax({
        url: "CommonAlumnoGetNotasAdicional",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#notas-div").html(data);
        },
        async: false
    });

    $("#modal-notas").modal("show");
    return false;
}

function showRequisitos(asignatura, nombre) {
    $("#requisitos-div").text($("#asignatura_input" + asignatura).val());
    $("#modal-requisitos").modal("show");
    return false;
}

$(document).ready(function () {
});