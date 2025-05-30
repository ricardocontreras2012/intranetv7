/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function downLoadDocumento() {
    $("#certificado-form").attr("action", "VerificacionCertificadoDownLoadDocumento")
    $("#certificado-form").attr("target", "_self");
    $("#certificado-form").attr("method", "post");
    $("#certificado-form").submit();
}

