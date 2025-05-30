/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function executeAction() {    
    $("#agno-sem-form").attr("action", $("#actionName").val());
    $("#agno-sem-form").attr("target", "_self").submit();
}

$(document).ready(function () {
    $("#next-button").click(executeAction);
    $("#sem-agno-modal").modal('show');
});