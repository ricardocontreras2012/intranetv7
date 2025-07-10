
function searchAlumno(pos) {
    $("#lista-form").attr("action", "CommonAlumnoSearchDirect?pos=" + pos + '&key=' + $("#keyDummy").val());
    $("#lista-form").attr("target", "_self").submit();
}

function fotoLista() {
    $("#lista-form").attr("action", 'CommonCursoListaFotoPrint?format=PDF&key=' + $("#keyDummy").val());
    $("#lista-form").attr("target", "_blank").submit();
}

function printLista() {
    $("#lista-form").attr("action", 'CommonCursoListaAsistenciaPrint?key=' + $("#keyDummy").val());
    $("#lista-form").attr("target", "_blank").submit();
}

function exportLista() {
    $("#lista-form").attr("action", 'CommonCursoListaExport?format=XLSX&contentDisposition=attachment;filename="' + $("#nombreDummy").val() + '.xlsx"&key=' + $("#keyDummy").val());
    $("#lista-form").attr("target", "_blank").submit();
}

$(document).ready(function () {
//Handlers
    $("#print-button").click(printLista);
    $("#export-button").click(exportLista);
    $("#foto-button").click(fotoLista);
    $("a").click(function () {
        const field_name = $(this).attr("id");
        searchAlumno(field_name.substr(field_name.indexOf("_") + 1));
    });
});