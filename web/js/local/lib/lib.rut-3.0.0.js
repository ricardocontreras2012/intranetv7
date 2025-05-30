

function formatear(rut, digitoVerificador)
{
    var sRut = new String(rut.replace(/[^0-9\Kk]/g, ''));
    var sRutFormateado = '';

    if (digitoVerificador) {
        var sDV = sRut.charAt(sRut.length - 1);
        sRut = sRut.substring(0, sRut.length - 1);
    }
    while (sRut.length > 3)
    {
        sRutFormateado = "." + sRut.substr(sRut.length - 3) + sRutFormateado;
        sRut = sRut.substring(0, sRut.length - 3);
    }
    sRutFormateado = sRut + sRutFormateado;
    if (sRutFormateado !== "" && digitoVerificador)
    {
        sRutFormateado += "-" + sDV;
    } else if (digitoVerificador)
    {
        sRutFormateado += sDV;
    }

    return sRutFormateado;
}


function checkDv(crut) {
    var largo, rut, suma, mul, res, dv, dvi, dvr, i;

    largo = crut.length;
    dvr = '0';
    suma = 0;
    mul = 2;

    if (largo < 2) {
        return false;
    }
    if (largo > 2) {
        rut = crut.substring(0, largo - 1);
    } else {
        rut = crut.charAt(0);
    }
    dv = crut.charAt(largo - 1);

    for (i = rut.length - 1; i >= 0; i -= 1) {
        suma += rut.charAt(i) * mul;
        if (mul === 7) {
            mul = 2;
        } else {
            mul += 1;
        }
    }
    res = suma % 11;
    if (res === 1) {
        dvr = 'k';
    } else {
        if (res === 0) {
            dvr = '0';
        } else {
            dvi = 11 - res;
            dvr = dvi + "";
        }
    }

    return dvr === dv.toLowerCase();

}

function validateRutDv(input, output) {
    var tmpstr, rutdv, i, rut, largo;

    tmpstr = "";
    rutdv = $("#" + input).val();

    for (i = 0; i < rutdv.length; i += 1) {
        if (rutdv.charAt(i) !== ' ' && rutdv.charAt(i) !== '.' && rutdv.charAt(i) !== '-') {
            tmpstr += rutdv.charAt(i);
        }
    }
    rutdv = tmpstr;
    largo = rutdv.length;

    tmpstr = "";
    for (i = 0; rutdv.charAt(i) === '0'; i += 1) {
    }

    for (; i < rutdv.length; i += 1) {
        tmpstr += rutdv.charAt(i);
    }
    rutdv = tmpstr;

    rut = rutdv.substring(0, largo - 1);

    if (!checkDv(rutdv)) {
        $("#" + output).val("");
        return false;
    }

    $("#" + output).val(rut);
    return true;
}