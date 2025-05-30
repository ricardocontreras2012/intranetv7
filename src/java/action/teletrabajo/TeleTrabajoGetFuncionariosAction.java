/*
 * @(#)TeletrabajoAsignarGetFuncionariosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.teletrabajo;

import static service.teletrabajo.TeleTrabajoGetFuncionariosService.service;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL TeletrabajoAsignarGetFuncionariosService
 *
 * @author Felipe
 * @version 1.0, 13/09/2023
 */
public final class TeleTrabajoGetFuncionariosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), Manager.getTeleTrabajoSession(sesion), getKey());
    }
}
