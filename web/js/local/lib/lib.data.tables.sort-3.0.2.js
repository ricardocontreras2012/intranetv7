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
    const ukDatea = a.split('/');
    const ukDateb = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(ukDatea[0],10))) {
        return 1;
    }

    if (isNaN(parseInt(ukDateb[0],10))) {
        return -1;
    }

    const x = parseInt(ukDatea[2] + ukDatea[1] + ukDatea[0], 10);
    const y = parseInt(ukDateb[2] + ukDateb[1] + ukDateb[0], 10);

    return ((x < y) ? -1 : ((x > y) ? 1 : 0));
};

jQuery.fn.dataTableExt.oSort['uk_date_short-desc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);
    const ukDatea = a.split('/');
    const ukDateb = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(ukDatea[0],10))) {
        return -1;
    }

    if (isNaN(parseInt(ukDateb[0],10))) {
        return 1;
    }

    const x = parseInt(ukDatea[2] + ukDatea[1] + ukDatea[0], 10);
    const y = parseInt(ukDateb[2] + ukDateb[1] + ukDateb[0], 10);

    return ((x < y) ? 1 : ((x > y) ? -1 : 0));
};

jQuery.fn.dataTableExt.oSort['uk_date_long-asc'] = function (a, b) {

    const ukDatea = a.split('/');
    const ukDateb = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(ukDatea[0],10))) {
        return 1;
    }

    if (isNaN(parseInt(ukDateb[0],10))) {
        return -1;
    }

    const x = parseInt(ukDatea[2] + ukDatea[1] + ukDatea[0], 10);
    const y = parseInt(ukDateb[2] + ukDateb[1] + ukDateb[0], 10);

    return ((x < y) ? -1 : ((x > y) ? 1 : 0));
};

jQuery.fn.dataTableExt.oSort['uk_date_long-desc'] = function (a, b) {
    const ukDatea = a.split('/');
    const ukDateb = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(ukDatea[0],10))) {
        return -1;
    }

    if (isNaN(parseInt(ukDateb[0],10))) {
        return 1;
    }

    const x = parseInt(ukDatea[2] + ukDatea[1] + ukDatea[0], 10);
    const y = parseInt(ukDateb[2] + ukDateb[1] + ukDateb[0], 10);

    return ((x < y) ? 1 : ((x > y) ? -1 : 0));
};


//FULL DATE
jQuery.fn.dataTableExt.oSort['uk_date_full-asc'] = function (a, b) {

    a = removeHTMLTags(a);
    b = removeHTMLTags(b);
    const ukDatea = a.split('/');
    const ukDateb = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(ukDatea[0],10))) {
        return 1;
    }

    if (isNaN(parseInt(ukDateb[0],10))) {
        return -1;
    }

    const agnoa = ukDatea[2].substr(0, 4);
    const horaa = ukDatea[2].substr(4).replace(":", "", "g").replace(" ", "").replace(".", "");
    const agnob = ukDateb[2].substr(0, 4);
    const horab = ukDateb[2].substr(4).replace(":", "", "g").replace(" ", "").replace(".", "");
    const x = parseInt(agnoa + ukDatea[1] + ukDatea[0] + horaa, 10);
    const y = parseInt(agnob + ukDateb[1] + ukDateb[0] + horab, 10);
    return ((x < y) ? -1 : ((x > y) ? 1 : 0));
};

jQuery.fn.dataTableExt.oSort['uk_date_full-desc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);

    const ukDatea = a.split('/');
    const ukDateb = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(ukDatea[0],10))) {
        return -1;
    }

    if (isNaN(parseInt(ukDateb[0],10))) {
        return 1;
    }

    const agnoa = ukDatea[2].substr(0, 4);
    const horaa = ukDatea[2].substr(4).replace(":", "", "g").replace(" ", "").replace(".", "");
    const agnob = ukDateb[2].substr(0, 4);
    const horab = ukDateb[2].substr(4).replace(":", "", "g").replace(" ", "").replace(".", "");
    const x = parseInt(agnoa + ukDatea[1] + ukDatea[0] + horaa, 10);
    const y = parseInt(agnob + ukDateb[1] + ukDateb[0] + horab, 10);

    return ((x < y) ? 1 : ((x > y) ? -1 : 0));
};
//


// NUMERIC_LINK
jQuery.fn.dataTableExt.oSort['numeric_link-asc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);

    const numeric_linka = a.split('-');
    const numeric_linkb = b.split('-');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(numeric_linka[0],10))) {
        return 1;
    }

    if (isNaN(parseInt(numeric_linkb[0],10))) {
        return -1;
    }
    const x = parseInt(numeric_linka[0], 10);
    const y = parseInt(numeric_linkb[0], 10);

    return ((x < y) ? -1 : ((x > y) ? 1 : 0));
};

jQuery.fn.dataTableExt.oSort['numeric_link-desc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);

    const numeric_linka = a.split('-');
    const numeric_linkb = b.split('-');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(numeric_linka[0],10))) {
        return -1;
    }

    if (isNaN(parseInt(numeric_linkb[0],10))) {
        return 1;
    }
    const x = parseInt(numeric_linka[0], 10);
    const y = parseInt(numeric_linkb[0], 10);

    return ((x < y) ? 1 : ((x > y) ? -1 : 0));
};
//

//ORDENAMIENTO CURSO
jQuery.fn.dataTableExt.oSort['asign_elect_coord_secc-asc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);
    const cursoa = a.split(' ');
    const cursob = b.split(' ');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(cursoa[0],10))) {
        return 1;
    }

    const x1 = parseInt(cursoa[0], 10);
    const x2 = cursoa[1];
    const x3 = cursoa[2];
    const x4 = parseInt(cursoa[3], 10);
    const y1 = parseInt(cursob[0], 10);
    const y2 = cursob[1];
    const y3 = cursob[2];
    const y4 = parseInt(cursob[3], 10);

    return ((x1 < y1) ? -1 : ((x1 > y1) ? 1 : ((x2 < y2) ? -1 : (x2 > y2) ? 1 : ((x3 < y3) ? -1 : ((x3 > y3) ? 1 : (((x4 < y4) ? -1 : ((x4 > y4) ? 1 : 0))))))));
};


jQuery.fn.dataTableExt.oSort['asign_elect-asc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);
    const cursoa = a.split(' ');
    const cursob = b.split(' ');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(cursoa[0],10))) {
        return 1;
    }

    const x1 = parseInt(cursoa[0], 10);
    const x2 = cursoa[1];
    const y1 = parseInt(cursob[0], 10);
    const y2 = cursob[1];

    return ((x1 < y1) ? -1 : ((x1 > y1) ? 1 : ((x2 < y2) ? -1 : (x2 > y2) ? 1 : 0)));
};

jQuery.fn.dataTableExt.oSort['asign_elect-desc'] = function (a, b) {
    a = removeHTMLTags(a);
    b = removeHTMLTags(b);
    const cursoa = a.split(' ');
    const cursob = b.split(' ');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(cursoa[0],10))) {
        return 1;
    }

    const x1 = parseInt(cursoa[0], 10);
    const x2 = cursoa[1];
    const y1 = parseInt(cursob[0], 10);
    const y2 = cursob[1];

    return ((x1 < y1) ? 1 : ((x1 > y1) ? -1 : ((x2 < y2) ? 1 : (x2 > y2) ? -1 : 0)));
};

jQuery.fn.dataTableExt.oSort['coord_secc-asc'] = function (a, b) {
    const coordSecca = a.split(' ');
    const coordSeccb = b.split(' ');
    const coorda = coordSecca[0];
    const coordb = coordSeccb[0];
    const secca = parseInt(coordSecca[1], 10);
    const seccb = parseInt(coordSecca[1], 10);

    return ((coorda < coordb) ? -1 : ((coorda > coordb) ? 1 : (((secca < seccb) ? -1 : ((secca > seccb) ? 1 : 0)))));
};

jQuery.fn.dataTableExt.oSort['coord_secc-desc'] = function (a, b) {
    const coordSecca = a.split(' ');
    const coordSeccb = b.split(' ');
    const coorda = coordSecca[0];
    const coordb = coordSeccb[0];
    const secca = parseInt(coordSecca[1], 10);
    const seccb = parseInt(coordSecca[1], 10);

    return ((coorda < coordb) ? 1 : ((coorda > coordb) ? -1 : (((secca < seccb) ? 1 : ((secca > seccb) ? -1 : 0)))));
};

jQuery.fn.dataTableExt.oSort['sem_agno-asc'] = function (a, b) {
    const semAgnoa = a.split('/');
    const semAgnob = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(semAgnoa[0],10))) {
        return 1;
    }

    if (isNaN(parseInt(semAgnob[0],10))) {
        return -1;
    }
    const x = 10 * semAgnoa[1] + semAgnoa[0];
    const y = 10 * semAgnob[1] + semAgnob[0];

    return ((x < y) ? -1 : ((x > y) ? 1 : 0));
};

jQuery.fn.dataTableExt.oSort['sem_agno-desc'] = function (a, b) {
    const semAgnoa = a.split('/');
    const semAgnob = b.split('/');

    //Treat blank/non date formats as highest sort
    if (isNaN(parseInt(semAgnoa[0],10))) {
        return -1;
    }

    if (isNaN(parseInt(semAgnob[0],10))) {
        return 1;
    }
    const x = 10 * semAgnoa[1] + semAgnoa[0];
    const y = 10 * semAgnob[1] + semAgnob[0];

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

