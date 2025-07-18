/*
 * @(#)CommonAsistenciaGetAsistenciasService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getDate;
import infrastructure.util.LogUtil;
import static infrastructure.util.SystemParametersUtil.DATE_FORMAT;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAsistenciaGetAsistenciasService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(GenericSession genericSession, String key){

        WorkSession ws = genericSession.getWorkSession(key);

        ws.setAsistenciaAlumnoList(ContextUtil.getDAO().getAsistenciaAlumnoPersistence(ActionUtil.getDBUser()).find(
                        ws.getCurso()));
        ws.setFechaActual(getDate(DATE_FORMAT));
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());   
        
        return SUCCESS;
    }
}
