function activateBlink()
{
    if(document.getElementById("navbarDropdown_utilidades").getAttribute("aria-expanded")) {
        blink($("#solictFirma"), 'red', 500, 7);
    }
}

$(document).ready(function () {
    $("#navbarDropdown_utilidades").click(activateBlink);
    unblockPage();
    showAlertMessages();
    loadIframeCentral();
});