/*
 * @(#)GetNotasAsignaturaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.LogUtil;
import static infrastructure.util.common.CommonMallaUtil.setCalificacionesAsignatura;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class GetNotasAsignaturaService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param asignatura
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Integer asignatura, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        setCalificacionesAsignatura(genericSession, asignatura, key);
        
        LogUtil.setLog(genericSession.getRut(), ws.getAluCar().getId().getAcaRut()+" "+asignatura.toString() );        

        return SUCCESS;
    }
}
