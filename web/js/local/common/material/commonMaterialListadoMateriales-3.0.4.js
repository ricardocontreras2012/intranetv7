
function showConfirmacion() {
    $("#confirmacion").modal('show');
}

function addMaterial() {
    $("#material-form").attr("action", "CommonMaterialAddMaterial").submit();
}

function showMaterial(tipo, material, key) {
    $("#material-form").attr("action", "CommonMaterialGetMaterial?key"+key+"&tipo="+tipo+"&material="+material).submit();
}

function removeMaterial() {
    $("#confirmacion").modal('hide');
    $("#material-form").attr("action", "CommonMaterialRemoveMateriales").submit();
}


function deleteMaterial() {
    if (anyChecked("material-form") === true) {
        showConfirmacion();
    } else {
        showAviso();
    }
}

$(document).ready(function () {
    $("#confirmacion").modal('hide');
});