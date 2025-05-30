/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.teletrabajo;

import static service.teletrabajo.TeleTrabajoEnviarRevisionMiActividadService.service;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Javier
 */
public class TeleTrabajoEnviarRevisionMiActividadAction extends ActionValidationPosSupport {
    private static final long serialVersionUID = 1L;
    private String actionCall;
    
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        actionCall = "TeleTrabajoGetMisActividades";
        return service(getGenericSession(), Manager.getTeleTrabajoSession(sesion), getKey(), getPos());
    }
    
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), Manager.getTeleTrabajoSession(sesion).getActividadList());
    }

    public String getActionCall() {
        return actionCall;
    }

    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }
    
}