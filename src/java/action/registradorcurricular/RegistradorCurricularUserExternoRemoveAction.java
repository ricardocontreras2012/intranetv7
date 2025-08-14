/*
 * @(#)RegistradorCurricularUserExternoRemoveAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.registradorcurricular;

import service.mantenedor.registradorcurricular.RegistradorCurricularUserExternoRemoveService;
import session.Manager;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL
 * RegistradorCurricularUserExternoRemove
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class RegistradorCurricularUserExternoRemoveAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new RegistradorCurricularUserExternoRemoveService().service(getGenericSession(), Manager.getRegistradorSession(sesion), getMapParameters(), getKey());
    }
}
