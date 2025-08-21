/*
 * @(#)GetSolicitudesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.solicitud.alumno;

import service.solicitud.alumno.AlumnoGetSolicitudesService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetSolicitudesAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new AlumnoGetSolicitudesService().service(getGenericSession(), getKey());
    }
}
