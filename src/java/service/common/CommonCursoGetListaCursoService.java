/*
 * @(#)CommonCursoGetListaCursoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

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
public final class CommonCursoGetListaCursoService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);  

        ws.setNominaCurso(ws.getCurso().getNominaAlumnos());  
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());     
        return SUCCESS;
    }
}
