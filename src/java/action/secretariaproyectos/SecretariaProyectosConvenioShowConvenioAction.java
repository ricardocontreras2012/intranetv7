/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.secretariaproyectos;

import static service.secretariaproyectos.SecretariaProyectosConvenioShowConvenioService.service;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosConvenioShowConvenioAction extends ActionValidationPosSupport {
    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), Manager.getProyectoSession(sesion), getPos(), getKey());
    }

    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), Manager.getProyectoSession(sesion).getConvenioList());
    }
}