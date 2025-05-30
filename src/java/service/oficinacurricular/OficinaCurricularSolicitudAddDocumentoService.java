/*
 * @(#)OficinaCurricularSolicitudAddDocumentoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.oficinacurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.SolicitudSupport;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class OficinaCurricularSolicitudAddDocumentoService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param tipoDocumento
     * @param upload
     * @param uploadFileName
     * @param caption
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     * @throws Exception Si el servico genera una exception.
     */
    public static String service(ActionCommonSupport action, GenericSession genericSession,
            Integer tipoDocumento, File upload, String uploadFileName, String caption, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);

        new SolicitudSupport(ws.getSolicitud()).doNewFile(action, tipoDocumento, upload, uploadFileName,
                caption);
        
        LogUtil.setLog(genericSession.getRut()," solicitud " + ws.getSolicitud().getSolFolio() + " documento "
                + uploadFileName);        
        return SUCCESS;
    }
}
