/*
 * @(#)AlumnoInscripcionRemoveInscripcionAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import service.alumno.AlumnoInscripcionRemoveInscripcionService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL
 * AlumnoInscripcionRemoveInscripcion
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoInscripcionRemoveInscripcionAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return new AlumnoInscripcionRemoveInscripcionService().service(this, getGenericSession(), getMapParameters(), getKey());
    }
}
