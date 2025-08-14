/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.secretariadocente;

import service.convalidacion.secretariadocente.SecretariaDocenteConvalidacionGetSolicitudService;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author rcontreras
 */
public class SecretariaDocenteConvalidacionGetSolicitudAction extends ActionValidationPosSupport {
    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SecretariaDocenteConvalidacionGetSolicitudService().service(getGenericSession(), Manager.getSecretariaSession(sesion), getPos(), getKey());
    }

    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), Manager.getSecretariaSession(sesion).getConvalidaciones());
    }
}