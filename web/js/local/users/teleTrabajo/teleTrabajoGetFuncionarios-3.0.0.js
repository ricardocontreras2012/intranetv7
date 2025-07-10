function getActividades(pos)
{
 $("#pos").val(pos);
 $("#funcionarios-form").attr("action", "TeleTrabajoGetActividades").attr("target", "_self").submit();
}

$(document).ready(function () {
    
    $("a").click(function () {
        const fieldName = $(this).attr("id");
        getActividades(fieldName.substr(fieldName.indexOf("_") + 1));
    });
});


