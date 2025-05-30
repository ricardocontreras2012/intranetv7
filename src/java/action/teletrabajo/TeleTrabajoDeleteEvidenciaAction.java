/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.teletrabajo;

import static service.teletrabajo.TeleTrabajoDeleteEvidenciaService.service;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Javier
 */
public class TeleTrabajoDeleteEvidenciaAction extends ActionValidationPosSupport {
    private static final long serialVersionUID = 1L;
    private Integer posTarea;
    private String actionCall;
    
    /**
     * Metodo que llama al servicio que realizara la eliminacion de la evidencia.
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        Integer correl = getPos();
        actionCall = "TeleTrabajoGetMiEvidencia";
        String retValue = service(getGenericSession(), Manager.getTeleTrabajoSession(sesion), getKey(), correl, posTarea);
        setPos(posTarea);
        return retValue;
    }
    
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(posTarea, Manager.getTeleTrabajoSession(sesion).getTareaList());
    }

    public Integer getPosTarea() {
        return posTarea;
    }

    public void setPosTarea(Integer posTarea) {
        this.posTarea = posTarea;
    }

    public String getActionCall() {
        return actionCall;
    }

    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }
    
}
