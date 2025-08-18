/*
 * @(#)AlumnoCambioMencionAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.inscripcion.alumno;

import service.inscripcion.alumno.AlumnoInscripcionCambioMencionService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL AlumnoInscripcionCambioMencion
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoCambioMencionAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return new AlumnoInscripcionCambioMencionService().service(getGenericSession(), Manager.getAlumnoSession(sesion), getKey());
    }
}
