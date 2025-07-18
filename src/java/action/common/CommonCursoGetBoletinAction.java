/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import infrastructure.support.action.ActionValidationAgnoSemSupport;
import service.common.CommonCursoGetBoletinService;

/**
 *
 * @author Javier Frez V.
 */
public class CommonCursoGetBoletinAction extends ActionValidationAgnoSemSupport {
    private static final long serialVersionUID = 1L;
    
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonCursoGetBoletinService().service(getGenericSession(), getKey(), getPos(), getAgno(), getSem());
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