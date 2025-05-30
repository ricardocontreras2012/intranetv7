/*
 * @(#)ProfesorReporteModifyReporteService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorReporteModifyReporteService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param sesionReporte
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, Integer sesionReporte, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        ws.setReporte(ws.getReportes().get(sesionReporte));
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        return SUCCESS;
    }
}
