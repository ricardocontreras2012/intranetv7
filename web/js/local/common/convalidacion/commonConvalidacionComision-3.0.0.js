function getProfesor()
{
    $("#comision-form").attr("action", "CommonProfesorSearchEnable?typeSearch=F");
    $("#comision-form").attr("target", "_self");
    $("#comision-form").submit();
}

function grabarComision(){
    $("#comision-form").attr("action", "CommonConvalidacionComisionSave");
    $("#comision-form").attr("target", "_self");
    $("#comision-form").submit();
}

$(document).ready(function() {
    $("#save-button").click(grabarComision);
    $("#add-button").click(getProfesor);
    
});
