
function swap(pos, curso) {
    $("#swap").modal('hide');
    $("#conf-title-swap-div").html("");
    $("#conf-title-swap-div").html($("#curso-title").val() + "<p></p><p>por</p>" + curso);
    $("#pos").val(pos);
    $("#confirmacion-swap").modal('show');

    return false;
}

function swapIns() {
    $("#confirmacion-swap").modal('hide');
    parent.$("#swap").modal('hide');

    $('#inscripcion-iframe', window.parent.document).attr("src", 'AlumnoInscripcionSwapInscripcion?key=' + $("#key").val()+"&pos="+$("#pos").val());
}


