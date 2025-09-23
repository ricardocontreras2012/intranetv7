/*
 * @(#)AlumnoGetInscripcionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.inscripcion.alumno;

import infrastructure.dto.InscripcionJsonDTO;
import infrastructure.support.InscripcionSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import session.GenericSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoGetInscripcionService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public InscripcionJsonDTO service(GenericSession genericSession, String key) {        
        return InscripcionSupport.getResponseFromJson(ContextUtil.getDAO().getInscripcionRepository(ActionUtil.getDBUser()).getInscripcionJson(genericSession.getWorkSession(key).getAluCar().getId()));                         
    }
}
