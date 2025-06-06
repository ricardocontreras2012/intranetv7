/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    //Handlers
    $("#caratula-button").click(generaCaratula);
    $("#pago-arancel-button").click(generaPagoArancel);

});

function generaCaratula() {
    //alert("click!");
    $("#expediente-form").attr("action", "AlumnoSolicitudExpedienteGeneraCaratula");
    $("#expediente-form").submit();
}

function generaPagoArancel() {
    //alert("click!");
    $("#expediente-form").attr("action", "AlumnoSolicitudExpedienteGeneraPagoArancel");
    $("#expediente-form").submit();
}