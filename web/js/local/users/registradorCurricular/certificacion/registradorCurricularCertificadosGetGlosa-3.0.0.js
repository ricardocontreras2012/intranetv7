
function getObs(alu, cert, tramite, obs, pos)
{
    $("#alu_id").val(alu);
    $("#cert_id").val(cert);
    $("#tramite_id").val(tramite);
    $("#obs").val(obs);
    $("#pos").val(pos);

    $("#glosa-modal").modal('show');
}

function emitir()
{
    $("#nomina-form").attr("action", "RegistradorCurricularCertificadosEmitirGlosa");
    $("#nomina-form").attr("target", "_self");
    $("#nomina-form").submit();
    $("#glosa-modal").modal('hide');
}