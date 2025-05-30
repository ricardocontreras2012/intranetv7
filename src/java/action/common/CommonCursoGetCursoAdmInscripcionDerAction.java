/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static service.common.CommonCursoGetCursoAdmInscripcionDerService.service;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Felipe
 */
public class CommonCursoGetCursoAdmInscripcionDerAction extends ActionValidationPosSupport {

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
        return service(getGenericSession(), Manager.getJefeCarreraSession(sesion), getPos(), getKey());
    }

    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(),
                getGenericSession().getWorkSession(getKey()).getCursoList());
    }

    /**
     * Method description
     *
     * @return
     */
    public String getActionCall() {
        return actionCall;
    }

    /**
     * Method description
     *
     * @param actionCall
     */
    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }
}
