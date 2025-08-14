/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.curso.CommonCursoGetCursoResumenService;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author rcontreras
 */
public class CommonCursoGetCursoResumenAction extends ActionValidationPosSupport {

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

        return new CommonCursoGetCursoResumenService().service(getGenericSession(), getPos(), getKey());
    }
    
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(),
                getGenericSession().getWorkSession(getKey()).getResumenCurso());
    }


    public String getActionCall() {
        return actionCall;
    }

    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }
}
