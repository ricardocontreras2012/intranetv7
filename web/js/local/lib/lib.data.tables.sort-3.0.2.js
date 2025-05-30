/*!
 *  dateTable functions
 *
 */
function removeHTMLTags(value) {
    value = value.replace(/&(lt|gt);/g, function (strMatch, p1) {
        return (p1 == "lt") ? "<" : ">";
    });
    return value.replace(/<\/?[^>]+(>|$)/g, "");
}

jQuery.fn.dataTableExt.oSort['uk_date_short-asc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);
    var ukDatea = a.split('/');
    var ukDateb = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(ukDatea[0],10))) {
        return 1;
    }

    if (isNaN(parseInt(ukDateb[0],10))) {
        return -1;
    }

    var x = parseInt(ukDatea[2] + ukDatea[1] + ukDatea[0],10);
    var y = parseInt(ukDateb[2] + ukDateb[1] + ukDateb[0],10);

    return ((x < y) ? -1 : ((x > y) ? 1 : 0));
};

jQuery.fn.dataTableExt.oSort['uk_date_short-desc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);
    var ukDatea = a.split('/');
    var ukDateb = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(ukDatea[0],10))) {
        return -1;
    }

    if (isNaN(parseInt(ukDateb[0],10))) {
        return 1;
    }

    var x = parseInt(ukDatea[2] + ukDatea[1] + ukDatea[0],10);
    var y = parseInt(ukDateb[2] + ukDateb[1] + ukDateb[0],10);

    return ((x < y) ? 1 : ((x > y) ? -1 : 0));
};

jQuery.fn.dataTableExt.oSort['uk_date_long-asc'] = function (a, b) {

    var ukDatea = a.split('/');
    var ukDateb = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(ukDatea[0],10))) {
        return 1;
    }

    if (isNaN(parseInt(ukDateb[0],10))) {
        return -1;
    }

    var x = parseInt(ukDatea[2] + ukDatea[1] + ukDatea[0],10);
    var y = parseInt(ukDateb[2] + ukDateb[1] + ukDateb[0],10);

    return ((x < y) ? -1 : ((x > y) ? 1 : 0));
};

jQuery.fn.dataTableExt.oSort['uk_date_long-desc'] = function (a, b) {
    var ukDatea = a.split('/');
    var ukDateb = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(ukDatea[0],10))) {
        return -1;
    }

    if (isNaN(parseInt(ukDateb[0],10))) {
        return 1;
    }

    var x = parseInt(ukDatea[2] + ukDatea[1] + ukDatea[0],10);
    var y = parseInt(ukDateb[2] + ukDateb[1] + ukDateb[0],10);

    return ((x < y) ? 1 : ((x > y) ? -1 : 0));
};


//FULL DATE
jQuery.fn.dataTableExt.oSort['uk_date_full-asc'] = function (a, b) {

    a = removeHTMLTags(a);
    b = removeHTMLTags(b);
    var ukDatea = a.split('/');
    var ukDateb = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(ukDatea[0],10))) {
        return 1;
    }

    if (isNaN(parseInt(ukDateb[0],10))) {
        return -1;
    }

    var agnoa = ukDatea[2].substr(0, 4);
    var horaa = ukDatea[2].substr(4).replace(":", "", "g").replace(" ", "").replace(".", "");
    var agnob = ukDateb[2].substr(0, 4);
    var horab = ukDateb[2].substr(4).replace(":", "", "g").replace(" ", "").replace(".", "");
    var x = parseInt(agnoa + ukDatea[1] + ukDatea[0] + horaa,10);
    var y = parseInt(agnob + ukDateb[1] + ukDateb[0] + horab,10);
    return ((x < y) ? -1 : ((x > y) ? 1 : 0));
};

jQuery.fn.dataTableExt.oSort['uk_date_full-desc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);

    var ukDatea = a.split('/');
    var ukDateb = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(ukDatea[0],10))) {
        return -1;
    }

    if (isNaN(parseInt(ukDateb[0],10))) {
        return 1;
    }

    var agnoa = ukDatea[2].substr(0, 4);
    var horaa = ukDatea[2].substr(4).replace(":", "", "g").replace(" ", "").replace(".", "");
    var agnob = ukDateb[2].substr(0, 4);
    var horab = ukDateb[2].substr(4).replace(":", "", "g").replace(" ", "").replace(".", "");
    var x = parseInt(agnoa + ukDatea[1] + ukDatea[0] + horaa,10);
    var y = parseInt(agnob + ukDateb[1] + ukDateb[0] + horab,10);

    return ((x < y) ? 1 : ((x > y) ? -1 : 0));
};
//


// NUMERIC_LINK
jQuery.fn.dataTableExt.oSort['numeric_link-asc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);

    var numeric_linka = a.split('-');
    var numeric_linkb = b.split('-');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(numeric_linka[0],10))) {
        return 1;
    }

    if (isNaN(parseInt(numeric_linkb[0],10))) {
        return -1;
    }
    var x = parseInt(numeric_linka[0],10);
    var y = parseInt(numeric_linkb[0],10);

    return ((x < y) ? -1 : ((x > y) ? 1 : 0));
};

jQuery.fn.dataTableExt.oSort['numeric_link-desc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);

    var numeric_linka = a.split('-');
    var numeric_linkb = b.split('-');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(numeric_linka[0],10))) {
        return -1;
    }

    if (isNaN(parseInt(numeric_linkb[0],10))) {
        return 1;
    }
    var x = parseInt(numeric_linka[0],10);
    var y = parseInt(numeric_linkb[0],10);

    return ((x < y) ? 1 : ((x > y) ? -1 : 0));
};
//

//ORDENAMIENTO CURSO
jQuery.fn.dataTableExt.oSort['asign_elect_coord_secc-asc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);
    var cursoa = a.split(' ');
    var cursob = b.split(' ');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(cursoa[0],10))) {
        return 1;
    }

    var x1 = parseInt(cursoa[0],10);
    var x2 = cursoa[1];
    var x3 = cursoa[2];
    var x4 = parseInt(cursoa[3],10);
    var y1 = parseInt(cursob[0],10);
    var y2 = cursob[1];
    var y3 = cursob[2];
    var y4 = parseInt(cursob[3],10);

    return ((x1 < y1) ? -1 : ((x1 > y1) ? 1 : ((x2 < y2) ? -1 : (x2 > y2) ? 1 : ((x3 < y3) ? -1 : ((x3 > y3) ? 1 : (((x4 < y4) ? -1 : ((x4 > y4) ? 1 : 0))))))));
};


jQuery.fn.dataTableExt.oSort['asign_elect-asc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);
    var cursoa = a.split(' ');
    var cursob = b.split(' ');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(cursoa[0],10))) {
        return 1;
    }

    var x1 = parseInt(cursoa[0],10);
    var x2 = cursoa[1];
    var y1 = parseInt(cursob[0],10);
    var y2 = cursob[1];

    return ((x1 < y1) ? -1 : ((x1 > y1) ? 1 : ((x2 < y2) ? -1 : (x2 > y2) ? 1 : 0)));
};

jQuery.fn.dataTableExt.oSort['asign_elect-desc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);
    var cursoa = a.split(' ');
    var cursob = b.split(' ');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(cursoa[0],10))) {
        return 1;
    }

    var x1 = parseInt(cursoa[0],10);
    var x2 = cursoa[1];
    var y1 = parseInt(cursob[0],10);
    var y2 = cursob[1];

    return ((x1 < y1) ? 1 : ((x1 > y1) ? -1 : ((x2 < y2) ? 1 : (x2 > y2) ? -1 : 0)));
};

jQuery.fn.dataTableExt.oSort['coord_secc-asc'] = function (a, b) {
    var coordSecca = a.split(' ');
    var coordSeccb = b.split(' ');
    var coorda = coordSecca[0];
    var coordb = coordSeccb[0];
    var secca = parseInt(coordSecca[1],10);
    var seccb = parseInt(coordSecca[1],10);

    return ((coorda < coordb) ? -1 : ((coorda > coordb) ? 1 : (((secca < seccb) ? -1 : ((secca > seccb) ? 1 : 0)))));
};

jQuery.fn.dataTableExt.oSort['coord_secc-desc'] = function (a, b) {
    var coordSecca = a.split(' ');
    var coordSeccb = b.split(' ');
    var coorda = coordSecca[0];
    var coordb = coordSeccb[0];
    var secca = parseInt(coordSecca[1],10);
    var seccb = parseInt(coordSecca[1],10);

    return ((coorda < coordb) ? 1 : ((coorda > coordb) ? -1 : (((secca < seccb) ? 1 : ((secca > seccb) ? -1 : 0)))));
};

jQuery.fn.dataTableExt.oSort['sem_agno-asc'] = function (a, b) {
    var semAgnoa = a.split('/');
    var semAgnob = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(semAgnoa[0],10))) {
        return 1;
    }

    if (isNaN(parseInt(semAgnob[0],10))) {
        return -1;
    }
    var x = 10 * semAgnoa[1] + semAgnoa[0];
    var y = 10 * semAgnob[1] + semAgnob[0];

    return ((x < y) ? -1 : ((x > y) ? 1 : 0));
};

jQuery.fn.dataTableExt.oSort['sem_agno-desc'] = function (a, b) {
    var semAgnoa = a.split('/');
    var semAgnob = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(semAgnoa[0],10))) {
        return -1;
    }

    if (isNaN(parseInt(semAgnob[0],10))) {
        return 1;
    }
    var x = 10 * semAgnoa[1] + semAgnoa[0];
    var y = 10 * semAgnob[1] + semAgnob[0];

    return ((x < y) ? 1 : ((x > y) ? -1 : 0));
};

jQuery.fn.dataTableExt.aTypes.push(
    function (sData) {
        if (sData.match(/^(0[1-9]|[12][0-9]|3[01])\-(0[1-9]|1[012])\-\d\d$/)) {
            return 'uk_date_short';
        }
        return null;
    }
);

jQuery.fn.dataTableExt.aTypes.push(
    function (sData) {
        if (sData.match(/^(0[1-9]|[12][0-9]|3[01])\-(0[1-9]|1[012])\-(19|20|21)\d\d$/)) {
            return 'uk_date_long';
        }
        return null;
    }
);

jQuery.fn.dataTableExt.aTypes.push(
    function (sData) {
        if (sData.match(/^(0[1-9]|[12][0-9]|3[01])\-(0[1-9]|1[012])\-(19|20|21)\d\d [0-2][0-9]\:[0-5][0-9]\:[0-5][0-9]\.[0-9]$/)) {
            return 'uk_date_full';
        }
        return null;
    }
);

jQuery.fn.dataTableExt.aTypes.push(
    function (sData) {
        if (sData.match(/^\d{1,8}[-][0-9|k|K]$/)) {
            return 'numeric_link';
        }
        return null;
    }
);

jQuery.fn.dataTableExt.aTypes.push(
    function (sData) {
        if (sData.match(/^\d{1,7}[ ]?$/)) {
            return 'asign_elect';
        }
        return null;
    }
);

jQuery.fn.dataTableExt.aTypes.push(
    function (sData) {
        if (sData.match(/^\?[ ]d{1,2}$/)) {
            return 'coord_secc';
        }
        return null;
    }
);

jQuery.fn.dataTableExt.aTypes.push(
    function (sData) {
        if (sData.match(/^[1-3][\/][0-9]{4}$/)) {
            return 'sem_agno';
        }
        return null;
    }
);

