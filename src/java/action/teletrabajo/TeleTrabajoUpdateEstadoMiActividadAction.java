/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.teletrabajo;

import static service.teletrabajo.TeleTrabajoUpdateEstadoMiActividadService.service;
import session.Manager;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 *
 * @author Administrador
 */
public class TeleTrabajoUpdateEstadoMiActividadAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return service(this, getGenericSession(), Manager.getTeleTrabajoSession(sesion), getMapParameters(), getKey());
    }
        
}