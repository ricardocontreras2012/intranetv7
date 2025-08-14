/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import java.util.List;
import service.inscripcion.CommonInscripcionAdmChangeNominaDerService;
import session.Manager;
import infrastructure.support.action.ActionParameterAwareSupport;
import java.util.Collection;

/**
 *
 * @author Felipe
 */
public class CommonInscripcionAdmChangeNominaDerAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;
    private String retValue;
    private Collection<String> errors;
    private Collection<String> errorMessages;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        retValue = new CommonInscripcionAdmChangeNominaDerService().service(this, getGenericSession(), Manager.getJefeCarreraSession(sesion), getMapParameters(), getKey());
        errors = this.getActionErrors();
        errorMessages = this.getActionMessages();
        return retValue;
    }

    public String getRetValue() {
        return retValue;
    }

    public void setRetValue(String retValue) {
        this.retValue = retValue;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Collection<String> getErrors() {
        return errors;
    }

    public Collection<String> getErrorMessages() {
        return errorMessages;
    }
    
    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

}
