/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.secretariadiplomados;

import service.login.secretariadiplomados.SecretariaApoyoDiplomadosLoginService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class SecretariaApoyoDiplomadosLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        return new SecretariaApoyoDiplomadosLoginService().service(getSesion(), getKey());
    }
}

