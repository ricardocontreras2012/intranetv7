/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function showAutoEvaluacion()
{
    $("#welcome-form").attr("action", "ProfesorAutoEvaluacionGetAutoEvaluacion");
    $("#welcome-form").attr("target", "_self");
    $("#welcome-form").submit();
}

function showWelCome()
{
    $("#autoevaluacion").modal("hide");
}

$(document).ready(function () {
    if ($("#sizeAutoEvaluacion").val() >0)
    {
        $("#autoevaluacion").modal("show");
    }
});


