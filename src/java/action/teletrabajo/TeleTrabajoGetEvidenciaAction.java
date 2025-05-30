/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.teletrabajo;

import static service.teletrabajo.TeleTrabajoGetEvidenciaService.service;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Javier
 */
public class TeleTrabajoGetEvidenciaAction extends ActionValidationPosSupport {
    private static final long serialVersionUID = 1L;
    
    
    
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        String retValue = service(getGenericSession(), Manager.getTeleTrabajoSession(sesion), getKey(), Manager.getTeleTrabajoSession(sesion).getTareaList().get(getPos()).getId());
        return retValue;
    }
    
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), Manager.getTeleTrabajoSession(sesion).getTareaList());
    }
}
