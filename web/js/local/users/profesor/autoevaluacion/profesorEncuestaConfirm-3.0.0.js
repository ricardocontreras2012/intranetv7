

function showForm()
{
    $("#confirm-form").attr("action", "ProfesorAutoEvaluacionGetAutoEvaluacion");
    $("#confirm-form").attr("target", "_self").submit();
    $("#confirmacion").modal('hide');
}


function stack()
{
    $("#confirm-form").attr("action", "CommonLoginStack");
    $("#confirm-form").attr("target", "_self").submit();
    $("#confirmacion").modal('hide');
}

function remove()
{
    $("#confirm-form").attr("action", "ProfesorAutoEvaluacionRemoveAutoEvaluacion");
    $("#confirm-form").attr("target", "_self").submit();
    $("#confirmacion").modal('hide');
}

$(document).ready(function () {
    $("#confirmacion").modal('show');
});



