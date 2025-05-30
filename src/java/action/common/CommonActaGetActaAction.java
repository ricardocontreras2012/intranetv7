/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static service.common.CommonActaGetActaService.service;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Administrador
 */
public class CommonActaGetActaAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {  
        return service(getGenericSession(), getPos(), getKey());
    }

    /**
     * Valida Parámetro.
     *
     * @return true: Si es válido y false: de lo contrario.
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getActaConsultaSupportList());
    }
}