

function importar()
{
    var data = new FormData($("#upload-form")[0]);
    jQuery.ajax({
        url: "SecretariaDocenteConvalidacionGetExcel",
        data: data,
        type: "POST",
        contentType: false,
        processData: false,
        success: function (data) {
            $("#center-div").html(data);
        },
        async: false
    });

    $("#upload-form").attr("action", "SecretariaDocenteConvalidacionGetExcel");
    $("#upload-form").attr("target", "_self");
    $("#upload-form").submit();
}

$(document).ready(function () {
    $("#importar-button").click(importar);
});