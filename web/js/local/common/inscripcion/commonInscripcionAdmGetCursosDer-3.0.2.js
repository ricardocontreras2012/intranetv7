function getCursoDer(posVal) {

    const dataString = {'key': $("#key").val(), 'pos': posVal, 'actionCall': 'CommonInscripcionAdmGetNominaDer'};
    jQuery.ajax({
        url: "CommonCursoGetCursoAdmInscripcionDer",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#nomina-der").html(data);
        },
        async: false
    });
}

$("#cursos-der-table a").click(function () {
    const fieldName = $(this).attr("id");
    getCursoDer(fieldName.substr(fieldName.indexOf("_") + 1));
});

$("#cursos-der-table").dataTable({
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



