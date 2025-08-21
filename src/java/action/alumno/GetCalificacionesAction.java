/*
 * @(#)GetCalificacionesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import service.alumno.GetCalificacionesService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonAlumnoGetCalificaciones
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetCalificacionesAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return  new GetCalificacionesService().service(getGenericSession(), getKey());
    }
}
