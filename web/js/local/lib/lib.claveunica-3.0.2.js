
function getClaveUnica()
{
    var userType = $("#usuarios option:selected").val();
    if (userType.length > 0)
    {
        callClaveUnica(userType);
    } else
    {
        $("#clave-unica-modal").modal('show');
    }
}

function getUser()
{
    callClaveUnica($("#claveunica-form :input[name='user']:checked").val());
}

function callClaveUnica(userType)
{
    var meta = JSON.stringify({"userType": userType});
    window.location = "https://services.usach.cl/clave-unica/authenticate?client_id=IrGzw7upcClM3hpnPTv9apNqkHAy5SzL&metadata=" + meta;
    target = "_self";
}

