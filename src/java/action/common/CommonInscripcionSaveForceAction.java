/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static service.common.CommonInscripcionSaveForceService.service;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Usach
 */
public class CommonInscripcionSaveForceAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;
    private String force;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getPos(), force, getKey());
    }

    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getAluCar().getInsList());
    }

    public void setForce(String force) {
        this.force = force;
    }
}
