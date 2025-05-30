/*
 * @(#)TeleTrabajoGetMisActividadesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.teletrabajo;

import static service.teletrabajo.TeleTrabajoGetMisActividadesService.service;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL TeleTrabajoGetMisActividadesService
 *
 * @author Felipe
 * @version 1.0, 13/09/2023
 */
public class TeleTrabajoGetMisActividadesAction extends ActionCommonSupport {
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