function verificaTopeHorarioCurso()
{
    const curso = $("#idCurso").val();
    const rut = $("#rut").val();
    const inicio = $("#fechaInicio").val();
    const termino = $("#fechaTermino").val();

    const dataString = {
        'key': $("#key").val(),
        'rut': rut,
        'curso': curso,
        'fechaInicio': inicio,
        'fechaTermino': termino
    };
    jQuery.ajax({
        url: "SecretariaProyectosConvenioTopeHorarioCurso",
        type: "GET",
        data: dataString,
        success: function (data) {
            $("#dummy").html(data);
        },
        async: false
    });

    const str = $("#result-status-div").html().trim();
    if (str === "Error")
    {
        $("#error-div").html("<p>" + $("#result-error-div").html().replaceAll("#", "</p>"));
        $("#curso-search-modal").modal('hide');
        $("#error-modal").modal('show');
        $('#tipoContrato').val('')[0].scrollIntoView();
    }
    else
    {
        let funcion;
        if ($("#tipoContrato").find("option:selected").val()==="DOC")
        {
            funcion="Relator";
        }
        else
        {
            funcion="Ayudante";
        }
        
        $("#curso-search-modal").modal('hide');
        $("#funcion").val(funcion+" del curso "+$("#curso").find("option:selected").text());
    }
}

$(document).ready(function () {
    $('#curso').on('change', function () {
        if ($(this).find("option:selected").val() >= 0)
        {
            $("#idCurso").val($(this).find("option:selected").val());
            verificaTopeHorarioCurso();
        }
    });
});
