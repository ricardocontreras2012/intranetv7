/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.convenio.secretariaproyectos;

import service.convenio.secretariaproyectos.SecretariaProyectosShowConvenioService;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosShowConvenioAction extends ActionValidationPosSupport {
    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SecretariaProyectosShowConvenioService().service(getGenericSession(), Manager.getProyectoSession(sesion), getPos(), getKey());
    }

    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), Manager.getProyectoSession(sesion).getConvenioList());
    }
}