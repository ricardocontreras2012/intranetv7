/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.curso.CommonCursoGetBoletinHorarioService;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Javier Frez V.
 */
public class CommonCursoGetBoletinHorarioAction extends ActionValidationPosSupport {
    private static final long serialVersionUID = 1L;
    
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonCursoGetBoletinHorarioService().service(getGenericSession(), getKey(), getPos());
    }
    
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getAluCarList());
    }
}
