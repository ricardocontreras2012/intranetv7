/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.certificacion.registradorcurricular;

import action.registradorcurricular.RegistradorCurricularCertificadosEmitirGlosaAction;
import domain.model.SolicitudCertificadoCarrito;
import session.GenericSession;
import session.RegistradorSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class RegistradorCurricularCertificadosEmitirGlosaService {

    public String service(RegistradorCurricularCertificadosEmitirGlosaAction action, GenericSession genericSession, RegistradorSession rs, Integer pos, String glosa) {
        rs.setCertificadoList(ContextUtil.getDAO().getSolicitudCertificadoCarritoRepository(ActionUtil.getDBUser()).find(genericSession.getUserType(), genericSession.getRut()));

        SolicitudCertificadoCarrito csc = rs.getCertificadoList().get(pos);
        ContextUtil.getDAO().getDummyRepository(ActionUtil.getDBUser()).setGlosa(csc.getId().getSccSolicitud(), csc.getId().getSccOrd(), glosa);
        action.setCorrel(csc.getId().getSccSolicitud());

        return csc.getTcertificado().getTceDesCorta(); 
    }
}
