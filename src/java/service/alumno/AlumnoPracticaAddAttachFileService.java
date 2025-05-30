/*
 * @(#)AlumnoPracticaAddAttachFileActionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Solicitud;
import java.io.File;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.SolicitudSupport;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoPracticaAddAttachFileService {

    /**
     * Method description
     *
     * @param action
     * @param genericSession Sesion de trabajo.
     * @param key LLave para aceder a los datos de la sesion.
     * @param upload
     * @param uploadFileName
     * @param tipo
     * @return Action status.
     */
    public static String service(ActionCommonSupport action, GenericSession genericSession, String key, File upload, String uploadFileName, Integer tipo) {

        WorkSession ws = genericSession.getWorkSession(key);
        Solicitud solicitud = ws.getSolicitud();

        try {
            new SolicitudSupport(ws.getSolicitud()).doNewFile(action, tipo, upload, uploadFileName, "");
            ws.getSolicitud().setSolicitudAttachList(ContextUtil.getDAO().getSolicitudAttachPersistence(ActionUtil.getDBUser()).find(solicitud));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }
}
