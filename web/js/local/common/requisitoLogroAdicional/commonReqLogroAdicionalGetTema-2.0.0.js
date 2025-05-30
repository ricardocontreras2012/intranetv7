

function next() {
    $("#inscripcion-form").attr("action", "CommonRequisitoAdicionalLogroSaveInscripcion");
    $("#inscripcion-form").attr("target", "_self");
    $("#inscripcion-form").submit();
}

$(document).ready(function () {


    //Handlers
    $("#next-button").click(next);

    $("#fecha").datepicker({
        showOn: 'both',
        buttonImage: '/intranetv7/images/local/icon/calendar.png',
        buttonImageOnly: true
        //,changeYear: true
    });
});
