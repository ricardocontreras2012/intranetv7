/*
 * @(#)AlumnoRemoveInscripcionAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.inscripcion.alumno;

import service.inscripcion.alumno.AlumnoRemoveInscripcionService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL
 * AlumnoInscripcionRemoveInscripcion
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoRemoveInscripcionAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return new AlumnoRemoveInscripcionService().service(this, getGenericSession(), getMapParameters(), getKey());
    }
}
