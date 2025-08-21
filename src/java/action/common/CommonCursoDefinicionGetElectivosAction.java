/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.curso.CommonCursoDefinicionGetElectivosService;
import infrastructure.support.action.ActionValidationAgnoSemSupport;

/**
 *
 * @author Administrador
 */
public class CommonCursoDefinicionGetElectivosAction extends ActionValidationAgnoSemSupport {

    private static final long serialVersionUID = 1L;
    
    private boolean isEconomia;

    public boolean isIsEconomia() {
        return isEconomia;
    }

    public void setIsEconomia(boolean isEconomia) {
        this.isEconomia = isEconomia;
    }

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonCursoDefinicionGetElectivosService().service(getGenericSession(), getKey(), getPos(), getAgno(), getSem(), this);
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getMiCarreraSupportList());
    }   
}

