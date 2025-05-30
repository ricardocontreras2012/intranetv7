

$(document).ready(function () {


    $("#region").change(function () {
        $.get('CommonComunaGetComunas', {'region': $(this).val(), 'key': $("#key").val()}, function (data) {
            $("#comunas").html(data);
        });
    });

    $("#region option[value='" + $("#regionDummy").val() + "']").prop("selected", true);
    $("#comuna option[value='" + $("#comunaDummy").val() + "']").prop("selected", true);

    $("#p06_otra").prop('disabled', true);
    $('#p06').change(function() {
        if($("#p06").val() === "f. Otra"){
            $("#p06_otra").prop('disabled', false);
        }
        else{
            $("#p06_otra").prop("disabled", true);
            $("#p06_otra").val("");
        }
    });

    $("#p07").change(function() {
        if($("#p07").val() === "1. Sí"){
            $("#p07_cual").prop("disabled", false);
        }
        else{
            $("#p07_cual").prop("disabled", true);
            $("#p07_cual").val("");
        }
    });

    $('#p08').change(function() {
        if($('#p08').val() === "1. Sí"){
            $("#p08_cual").prop('disabled', false);
        }
        else{
            $("#p08_cual").prop('disabled', true);
            $('#p08_cual').val("");
        }
    });

    $("#p11_1_cual").prop('disabled', true);
    $('#p11_1').change(function() {
        if($('#p11_1').val() === "e. Otro"){
            $("#p11_1_cual").prop('disabled', false);
        }
        else{
            $("#p11_1_cual").prop('disabled', true);
            $('#p11_1_cual').val("");
        }
    });

    $("#p13_cual").prop('disabled', true);
    $('#p13').change(function() {
        if($('#p13').val() === "g) Otra situación"){
            $("#p13_cual").prop('disabled', false);
        }
        else{
            $("#p13_cual").prop('disabled', true);
            $('#p13_cual').val("");
        }
    });

    $("#p15_cual").prop('disabled', true);
    $('#p15').change(function() {
        if($('#p15').val() === "7. Otra situación"){
            $("#p15_cual").prop('disabled', false);
        }
        else{
            $("#p15_cual").prop('disabled', true);
            $('#p15_cual').val("");
        }
    });

    $("#p21_cual").prop('disabled', true);
    $('#p21').change(function() {
        if($('#p21').val() === "g) Otra razón"){
            $("#p21_cual").prop('disabled', false);
        }
        else{
            $("#p21_cual").prop('disabled', true);
            $('#p21_cual').val("");
        }
    });

    $("#p22_cual").prop('disabled', true);
    $('#p22').change(function() {
        if( ($('#p22').val() === "a) Prensa escrita") || ($('#p22').val() === "b) Televisión") || ($('#p22').val() === "c) Radio") || ($('#p22').val() === "d) Internet") || ($('#p22').val() === "h) Otro") )
        {
            $("#p22_cual").prop('disabled', false);
        }
        else {
            $("#p22_cual").prop('disabled', true);
            $('#p22_cual').val("");
        }
    });

    $("#p23_cual").prop('disabled', true);
    $('#p23').change(function() {
        if($('#p23').val() === "m) Otra razón"){
            $("#p23_cual").prop('disabled', false);
        }
        else{
            $("#p23_cual").prop('disabled', true);
            $('#p23_cual').val("");
        }
    });

    $("#p24_cual").prop('disabled', true);
    $('#p24').change(function() {
        if( ($('#p24').val() === "d) Por los medios de comunicación") || ($('#p24').val() === "e) Por las redes sociales") || ($('#p24').val() === "f) Otra") ){
            $("#p24_cual").prop('disabled', false);
        }
        else{
            $("#p24_cual").prop('disabled', true);
            $('#p24_cual').val("");
        }
    });

    $("#p25_cual").prop('disabled', true);
    $('#p25').change(function() {
        if($('#p25').val() === "e) Otra"){
            $("#p25_cual").prop('disabled', false);
        }
        else{
            $("#p25_cual").prop('disabled', true);
            $('#p25_cual').val("");
        }
    });

    $("#p27_cual").prop('disabled', true);
    $('#p27').change(function() {
        if($('#p27').val() === "g) Otra"){
            $("#p27_cual").prop('disabled', false);
        }
        else{
            $("#p27_cual").prop('disabled', true);
            $('#p27_cual').val("");
        }
    });

    $("#p28_cual").prop('disabled', true);
    $('#p28').change(function() {
        if($('#p28').val() === "e) Otra"){
            $("#p28_cual").prop('disabled', false);
        }
        else{
            $("#p28_cual").prop('disabled', true);
            $('#p28_cual').val("");
        }
    });

    $("#p30 option").eq(1).prop("selected", true);
    $('#p29').change(function() {
        if($('#p29').val() === "e) No trabajo"){
            $("#p30 option").eq(0).prop("selected", true);
        }
        else{
            $("#p30 option").eq(1).prop("selected", true);
        }
    });

    $("#p30_cual").prop('disabled', true);
    $('#p30').change(function() {
        if($('#p30').val() === "e) Otra"){
            $("#p30_cual").prop('disabled', false);
        }
        else{
            $("#p30_cual").prop('disabled', true);
            $('#p30_cual').val("");
        }
    });

    $('#p40').change(function() {
        if($('#p40').val() === "a) Sí"){
            $("#p41").prop('disabled', false);
        }
        else{
            $("#p41").prop('disabled', true);
            $('#p41').val("");
        }
    });

    $("#p50_cual").prop('disabled', true);
    $('#p50').change(function() {
        if($('#p50').val() === "e) Otro"){
            $("#p50_cual").prop('disabled', false);
        }
        else{
            $("#p50_cual").prop('disabled', true);
            $('#p50_cual').val("");
        }
    });

    $("#p61_cual").prop('disabled', true);
    $('#p61').change(function() {
        if($('#p61').val() === "e) Otra"){
            $("#p61_cual").prop('disabled', false);
        }
        else{
            $("#p61_cual").prop('disabled', true);
            $('#p61_cual').val("");
        }
    });

    $("#p62_cual").prop('disabled', true);
    $('#p62').change(function() {
        if($('#p62').val() === "f) Otra"){
            $("#p62_cual").prop('disabled', false);
        }
        else{
            $("#p62_cual").prop('disabled', true);
            $('#p62_cual').val("");
        }
    });

    $('#p63').change(function() {
        if($('#p63').val() === "a) Sí"){
            $("#p64_cual").prop('disabled', false);
        }
        else{
            $("#p64_cual").prop('disabled', true);
            $('#p64_cual').val("");
        }
    });

    $("#p66_cual").prop('disabled', true);
    $('#p66').change(function() {
        if($('#p66').val() === "e) Otra"){
            $("#p66_cual").prop('disabled', false);
        }
        else{
            $("#p66_cual").prop('disabled', true);
            $('#p66_cual').val("");
        }
    });

    $("#cuestionario-form").validate({
        event: "blur",
        rules: {
            p01_anos: {
                digits: true
            },
            p01_meses: {
                digits: true
            },
            p03_a: {
                digits: true
            },
            p03_b: {
                digits: true
            },
            p09: {
                digits: true
            },
            p41: {
                digits: true
            }
        },
        messages: {
            p01_anos: {
                digits: jQuery.validator.messages.digits
            },
            p01_meses: {
                digits: jQuery.validator.messages.digits
            },
            p03_a: {
                digits: jQuery.validator.messages.digits
            },
            p03_b: {
                digits: jQuery.validator.messages.digits
            },
            p09: {
                digits: jQuery.validator.messages.digits
            },
            p41: {
                digits: jQuery.validator.messages.digits
            }
        }
    });
});