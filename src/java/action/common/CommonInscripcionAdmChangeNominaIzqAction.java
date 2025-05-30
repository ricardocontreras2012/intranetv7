/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import java.util.List;
import static service.common.CommonInscripcionAdmChangeNominaIzqService.service;
import session.Manager;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 *
 * @author Felipe
 */
public class CommonInscripcionAdmChangeNominaIzqAction extends ActionParameterAwareSupport {
    private static final long serialVersionUID = 1L;
    private String retValue;
    private List<String> errors;
    private List<String> errorMessages;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        retValue = service(this, getGenericSession(), Manager.getJefeCarreraSession(sesion) , getMapParameters(), getKey());
        errors = (List<String>) this.getActionErrors();
        errorMessages = (List<String>) this.getActionMessages();
        return retValue;
    }

    public String getRetValue() {
        return retValue;
    }

    public void setRetValue(String retValue) {
        this.retValue = retValue;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
    
}
