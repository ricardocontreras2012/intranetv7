/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.common.CommonAgnoSemService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class CommonAgnoSemAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    private String actionCall;
    private String actionName;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {        
        return new CommonAgnoSemService().service(getGenericSession(),actionCall, getKey());
    }    

    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}
