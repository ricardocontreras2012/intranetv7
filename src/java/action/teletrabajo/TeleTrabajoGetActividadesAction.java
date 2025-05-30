/*
 * @(#)TeletrabajoAsignarGetHorarioAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.teletrabajo;

import static service.teletrabajo.TeleTrabajoGetActividadesService.service;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL CommonSalaReservaGetHorario
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class TeleTrabajoGetActividadesAction extends ActionValidationPosSupport {
    private static final long serialVersionUID = 1L;
    
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), Manager.getTeleTrabajoSession(sesion), getPos(), getKey());
    }
    
     /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException { 
        return isValidPos(getPos(), Manager.getTeleTrabajoSession(sesion).getFuncionarioList());
        
    }
}
