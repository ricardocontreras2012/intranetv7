/*
 * @(#)AlumnoInscripcionAddInscripcionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.inscripcion.alumno;

import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoInscripcionAddInscripcionService {

    /**
     * Method Servicio
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(ActionCommonSupport action, GenericSession genericSession,
            Integer pos, String key)
    {
        WorkSession ws = genericSession.getWorkSession(key);       
        
        return ws.getAluCar().addInscripcionAlumno(action, genericSession, pos, ws.getDerecho());        
    }
}
