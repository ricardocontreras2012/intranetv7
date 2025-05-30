
function showMaterial(user) {
    $("#tipoMaterial").val(user);
    $("#material-form").attr("action", "CommonProfesorCargaHistoricaMaterial").submit();  
}

