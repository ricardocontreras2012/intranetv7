/*
 * @(#)AlumnoInscripcionGetDerechosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import service.inscripcion.alumno.AlumnoInscripcionGetDerechosService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL AlumnoInscripcionGetDerechos
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoInscripcionGetDerechosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return new AlumnoInscripcionGetDerechosService().service(getGenericSession(), getKey());
    }
}
