function showMaterial()
{
    $("#material").modal('show');
}

function closeMsg()
{
    $("#material").modal('hide');
}
function executeGetMaterial(a, tipoMat) {
    closeMsg();
    const b = {key: $("#keyDummy").val(), tipo: tipoMat};
    jQuery.ajax({url: a, type: "POST", data: b, success: function (a) {
            $("#menu-inner-iframe").attr("srcdoc", a);
        }, async: !1});
}

$(document).ready(function () {
    $("#material").modal('hide');
});

