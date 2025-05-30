
function getCursos()
{
    $("#cursos-form").attr("action", "CommonActaComplementariaGetCursos").attr("target", "_self").submit();
}

function emitirActa()
{
    $("#cursos-form").attr("action", "CommonActaComplementariaEmitirActa").attr("target", "_self").submit();
}

$(document).ready(function () {
    $("#emitir-button").click(emitirActa);
    $("#search-button").click(getCursos);

    var msgError = $("#msg-dummy-error-div").text().replace(/(\r\n|\n|\r)/g, "");
    if (msgError !== '')
    {
        $("#msg-result-div").html("<div class='actionError'><ul><li><span>" + msgError + "</li></ul></span></div>");
        $("#result").modal('show');
    }

    var msgOK = $("#msg-dummy-ok-div").text().replace(/(\r\n|\n|\r)/g, "");
    if (msgOK !== '')
    {
        $("#msg-result-div").html("<div class='actionMessage'><ul><li><span>" + msgOK + "</li></ul></span></div>");
        $("#result").modal('show');
    }
    
    $("#cursos-form :input").keydown(function (e) {
        if (enterKey(e)) {
            $("#cursos-form").valid();
            var fields = $(this).parents('form:eq(0),body').find('button,input,textarea,select');
            var index = fields.index(this);
            if (index > -1 && (index + 1) < fields.length) {
                fields.eq(index + 1).focus();
            }
        }
    });
    
    $("#cursos-form").validate({
        validationEventTriggers: "blur",        
        event: "blur",
        rules: {},
        messages: {}
    });

    $("#cursos-form :input").each(function () {
        var field_name = $(this).attr("id");
        if (field_name.startsWith("nota_")) {
            $(this).rules("add", {
                formatoNota: true
            });
        }
    });
    
     $("#cursos-form :input").change(function () {
        var field_name = $(this).attr("id");
        if (field_name.startsWith("nota_")) {
             var value = $("#"+field_name).val().replace(",",".");         
             if (value > 10)
             {
                 value = value/10;
             }
            (value < 4) ? $("#"+field_name).attr("class", "form-control no-padding col-lg-6 col-sm-12 reprobado") : $("#"+field_name).attr("class", "form-control no-padding col-lg-6 col-sm-12 aprobado");
        }
    });

});



