/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.convalidacion.secretariadocente;


import service.convalidacion.secretariadocente.SecretariaDocenteEnableComisionService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author rcontreras
 */
public class SecretariaDocenteEnableComisionAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        return new SecretariaDocenteEnableComisionService().service(Manager.getSecretariaSession(sesion));
    }
}
