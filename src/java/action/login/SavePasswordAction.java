/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.login;

import service.login.SavePasswordService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class SavePasswordAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    private String passwdActual;
    private String passwdNueva;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SavePasswordService().service(this, getGenericSession(), passwdActual, passwdNueva);
    }

    public String getPasswdActual() {
        return passwdActual;
    }

    public void setPasswdActual(String passwdActual) {
        this.passwdActual = passwdActual;
    }    

    public String getPasswdNueva() {
        return passwdNueva;
    }

    public void setPasswdNueva(String passwdNueva) {
        this.passwdNueva = passwdNueva;
    }
}
