/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static service.common.CommonCursoGetCursosxAgnoSemEncuestaService.service;
import infrastructure.support.action.ActionValidationAgnoSemSupport;

/**
 *
 * @author Administrador
 */
public class CommonCursoGetCursosxAgnoSemEncuestaAction extends ActionValidationAgnoSemSupport {

    private static final long serialVersionUID = 1L;
    
    private String tipo;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey(), getPos(), getAgno(), getSem());
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
