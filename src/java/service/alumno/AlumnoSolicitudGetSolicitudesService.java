/*
 * @(#)AlumnoSolicitudGetSolicitudesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoSolicitudGetSolicitudesService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, String key){
        WorkSession ws = genericSession.getWorkSession(key);        
        ws.setSolicitudList(ContextUtil.getDAO().getSolicitudPersistence(ActionUtil.getDBUser()).find(ws.getAluCar()));
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }
}
