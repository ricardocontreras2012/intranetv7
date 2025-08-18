/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.convalidacion.secretariadocente;

import service.convalidacion.secretariadocente.SecretariaDocenteGetSolicitudesService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author rcontreras
 */
public class SecretariaDocenteGetSolicitudesAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SecretariaDocenteGetSolicitudesService().service(getGenericSession(), Manager.getSecretariaSession(sesion), getKey());
    }
}