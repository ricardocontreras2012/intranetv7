function getCursoIzq(posVal) {

    const dataString = {'key': $("#key").val(), 'pos': posVal, 'actionCall': 'CommonInscripcionAdmGetNominaIzq'};
    jQuery.ajax({
        url: "CommonCursoGetCursoAdmInscripcionIzq",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#nomina-izq").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmGetCursosDer",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#cursos-der").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmDummy",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#nomina-der").html(data);
        },
        async: false
    });

    $("#pos").val(posVal);
}

function showProfesor(profesor, pos, source) {

    const random_number = Math.floor(Math.random() * 10000);
    const html_text = "<div><img width=\"70\" height=\"80\" alt=\" \" src=\"dummy/" + random_number + "/intranetv7/CommonInscripcionGetFotoProfesor?key=" + $("#key").val() + "&pos=" + pos + "&source=" + source + "\"/><p>" + profesor.replace('/0/g', ' ') + "</p></div>";

    $("#profesor-div").html(html_text);
    $("#profesor").modal('show');
    return false;
}

$("a").click(function () {
    const fieldName = $(this).attr("id");
    getCursoIzq(fieldName.substr(fieldName.indexOf("_") + 1));
});

$("#cursos-izq-table").dataTable({
    "paging": false,
    "aLengthMenu": [
            [4, 10, 20, -1],
            [4, 10, 20, "All"]
        ],
    "oLanguage": {
        "sProcessing": "Procesando...",
        "sLengthMenu": "Mostrar _MENU_ registros",
        "sZeroRecords": "No se encontraron resultados",
        "sInfo": "Mostrando desde _START_ hasta _END_ de _TOTAL_ registros",
        "sInfoEmpty": "Mostrando desde 0 hasta 0 de 0 registros",
        "sInfoFiltered": "(filtrado de _MAX_ registros en total)",
        "sInfoPostFix": "",
        "sSearch": "Buscar:",
        "sUrl": ""
    },
    "aoColumns": [
        {
            "sSortDataType": "html",
            "sType": "numeric_link"
        },
        
        {
            "sType": null
        },
        {
            "sType": null
        },
        {
            "sType": null
        },
        {
            "sType": null
        }
    ],
    "iDisplayLength": 4
});

$(document).ready(function () {
    let pos = $("#pos").val();

    if (pos !== null && pos > 0)
    {
        const rows = document.querySelectorAll('#cursos-izq-table tr');
        pos++;
        rows[pos].scrollIntoView({
            behavior: 'auto',
            block: 'start'
        });
    }
});