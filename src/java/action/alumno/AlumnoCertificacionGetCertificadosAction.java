/*
 * @(#)AlumnoCertificacionGetCertificadosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import static service.alumno.AlumnoCertificacionGetCertificadosService.service;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL AlumnoCertificacionGetTramites
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoCertificacionGetCertificadosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){
        return service(getGenericSession(), Manager.getAlumnoSession(sesion), getKey());
    }
}
