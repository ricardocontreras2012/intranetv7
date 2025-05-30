/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function generaActas()
{
    $("#form-conf").attr("action", "RegistradorCurricularActaGenerarIntercambio");
    $("#form-conf").attr("target", "_self");
    $("#form-conf").submit();
}

$(document).ready(function () {
    $("#conf-emision-actas").modal('show');
});


