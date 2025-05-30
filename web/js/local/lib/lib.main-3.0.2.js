/*!
 * lib.main Library
 *
 * Copyright 2010, FAE-USACH.
 *
 * Date: 10-05-2010
 */

String.prototype.startsWith = function (prefix) {
    return (this.substr(0, prefix.length) === prefix);
};

function isEmpty(str) {
    return (!str || 0 === str.length);
}

function checkAll(form) {    
    $("#" + form + " input[type=checkbox]").each(function () {
        if ($(this).attr("id").startsWith("ck")) { 
            $(this).prop('checked', true);
        }
    });
}

function uncheckAll(form) {
    $("#" + form + " input[type=checkbox]").each(function () {
        if ($(this).attr("id").startsWith("ck")) {
            $(this).prop('checked', false);
        }
    });
}

function checkingHead(form) {
    if ($("#checkHeadInput").is(":checked")) {
        checkAll(form);
    } else {
        uncheckAll(form);
    }
}

function anyChecked(form) {
    return ($("#" + form + " input[type=checkbox]:checked").length > 0);
}

function checkAllTipo(form, tipo) {
    $("#" + form + " input[type=checkbox]").each(function () {
        if ($(this).attr("id").startsWith("ck_" + tipo + "_")) {
            $(this).prop('checked', true);
        }
    });
}

function uncheckAllTipo(form, tipo) {
    $("#" + form + " input[type=checkbox]").each(function () {
        if ($(this).attr("id").startsWith("ck_" + tipo + "_")) {
            $(this).prop('checked', false);
        }
    });
}

function checkingHeadTipo(form, tipo) {
    if ($("#checkHeadInput" + tipo).is(":checked")) {
        checkAllTipo(form, tipo);
    } else {
        uncheckAllTipo(form, tipo);
    }
}

function passwordFormat() {
    if (this.value.match(/[^a-zA-Z0-9@#\$%&+=_\-]/g)) {
        this.value = this.value.replace(/[^a-zA-Z0-9@#\$%&+=_\-]/g, '');
    }
}

function enterKey(e) {
    return ((e.keyCode ? e.keyCode : e.which) === 13);
}

jQuery.fn.resetForm = function () {
    $(this).each(function () {
        this.reset();
    });

    $(this).find('iframe').each(function () {
        $(this).attr("src", "");
    });
};

function blink(target, backgroundColor, interval, times) {
    var existingBgColor = target.css('background-color'), i;

    for (i = 0; i !== times; ++i) {
        setTimeout(function () {
            target.css('background-color', backgroundColor);
        }, interval * i * 2);
        setTimeout(function () {
            target.css('background-color', existingBgColor);
        }, interval * (i * 2 + 1));
    }
}

function loadIframeCentral() {
    $("#main-content-iframe").css('height', '99%');
    $("#main-content-iframe").css('width', '100%');
    $("#main-content-iframe").attr("src", "/intranetv7/CommonLoginIframeContent?key=" + $("#keyDummy").val());
}

function loadSearch(typeSearch, action, pos, key)
{
    var dest;
    if (typeSearch === "F") {
        dest = "#main-content-iframe";
    } else {
        dest = "#message-content-iframe";
    }
    $(dest, window.parent.parent.document).attr("src", action + "?pos=" + pos + '&key=' + key);
}

function blockPage()
{
    //waitingDialog.show('Cargando...', {dialogSize: 'sm', headerSize: 5});
}

function unblockPage()
{
    //waitingDialog.hide();
}

function randomString(length, chars) {
    var mask = '';
    if (chars.indexOf('a') > -1)
        mask += 'abcdefghijklmnopqrstuvwxyz';
    if (chars.indexOf('A') > -1)
        mask += 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    if (chars.indexOf('#') > -1)
        mask += '0123456789';
    if (chars.indexOf('!') > -1)
        mask += '~`!@#$%^&*()_+-={}[]:";\'<>?,./|\\';
    var result = '';
    for (var i = length; i > 0; --i)
        result += mask[Math.floor(Math.random() * mask.length)];
    return result;
}


function browser()
{
    var userAgent = navigator.userAgent;
    var browserName;

    if (userAgent.match(/chrome|chromium|crios/i)) {
        browserName = "chrome";
    } else if (userAgent.match(/firefox|fxios/i)) {
        browserName = "firefox";
    } else if (userAgent.match(/safari/i)) {
        browserName = "safari";
    } else if (userAgent.match(/opr\//i)) {
        browserName = "opera";
    } else if (userAgent.match(/edg/i)) {
        browserName = "edge";
    } else {
        browserName = "No browser detection";
    }

    return browserName;
}

function blockBack()
{
    /*var navegador = browser();

    history.pushState(null, null, location.href);
    if (!(navegador === "firefox"))
    {
        history.back();
    }
    history.forward();
    window.onpopstate = function () {
        history.go(1);
    };*/
}
