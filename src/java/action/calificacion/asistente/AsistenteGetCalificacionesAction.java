/*
 * @(#)AsistenteGetCalificacionesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.calificacion.asistente;

import service.calificacion.asistente.AsistenteGetCalificacionesService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL AsistenteGetCalificaciones
 *
 * @author Felipe
 * @version 7, 17/10/2023
 */
public class AsistenteGetCalificacionesAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new AsistenteGetCalificacionesService().service(getGenericSession(), getMapParameters(), getKey());
    }
}

