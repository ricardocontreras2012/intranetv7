/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.registradorcurricular;

import static service.registradorcurricular.RegistradorCurricularMencionGetService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Usach
 */
public class RegistradorCurricularMencionGetAction  extends ActionCommonSupport {
   private String actionCall;
    
    @Override
    public String action() throws Exception {   
        return service(getGenericSession(), getKey());
    }

    public String getActionCall() {
        return actionCall;
    }

    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }
}