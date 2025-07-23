/*
 * @(#)VerificacionCertificadoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.verificacioncertificado;

import static com.opensymphony.xwork2.Action.NONE;
import domain.model.LogCertificacion;
import java.util.Map;
import session.VerificacionCertificadoSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class VerificacionCertificadoService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param sesionMap
     * @param folio
     * @param verificador
     * @return Action status.
     */
    public String service(ActionCommonSupport action, Map<String, Object> sesionMap, Integer folio,
            String verificador) {

        VerificacionCertificadoSession session;       
        
        session = (VerificacionCertificadoSession) sesionMap.get("verificacionCertificadoSession");
        if (session == null) {
            session = new VerificacionCertificadoSession();
            sesionMap.put("verificacionCertificadoSession", session);
        }  

        LogCertificacion log = ContextUtil.getDAO().getLogCertificacionRepository("VC").find(folio,
                verificador.toUpperCase(ContextUtil.getLocale()));

        session.setLogCertificacion(log);
        session.setDownload(Boolean.FALSE);
        session.setArchivo(null);

        String retValue = NONE;
        String fileFull = "";         

        if (log == null) {
            action.addActionError(action.getText("error.certificado.invalido"));
        } else {                     
            retValue = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getCert(log.getLcertTcertificado());            
            fileFull = CommonCertificacionUtil.getNameFile(log.getAluCar(), folio, log.getLcertTcertificado());
        }         
        
        if (!NONE.equals(retValue) && !"".equals(fileFull)) {
            if (CommonArchivoUtil.exists(PATH_CERT + fileFull)) {
                session.setDownload(Boolean.TRUE);
                session.setArchivo(fileFull);
            }
        }        

        return retValue;

    }
}
