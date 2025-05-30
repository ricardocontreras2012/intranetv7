/*
 * @(#)OficinaCurricularSolicitudEnableReceiveSolicitudAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.oficinacurricular;

import static service.oficinacurricular.OficinaCurricularSolicitudEnableReceiveSolicitudService.service;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL
 * OficinaCurricularSolicitudEnableReceiveSolicitud
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class OficinaCurricularSolicitudEnableReceiveSolicitudAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getPos(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getSolicitudList());
    }
}
