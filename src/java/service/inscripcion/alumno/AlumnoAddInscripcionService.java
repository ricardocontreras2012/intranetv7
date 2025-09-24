/*
 * @(#)AlumnoAddInscripcionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.inscripcion.alumno;

import infrastructure.dto.InscripcionJsonDTO;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoAddInscripcionService {


    public InscripcionJsonDTO service(ActionCommonSupport action, GenericSession genericSession,
            Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem, String comp, String key)
    {        
        WorkSession ws = genericSession.getWorkSession(key);       
        
        return ws.getAluCar().addInscripcionAlumno(action, genericSession, asign, elect, coord, secc, agno, sem, comp, ws.getDerecho());        
    }
}
