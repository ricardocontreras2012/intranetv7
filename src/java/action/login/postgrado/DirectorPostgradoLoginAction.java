/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.login.postgrado;

import service.login.postgrado.DirectorPostgradoLoginService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author rcontreras
 */
public class DirectorPostgradoLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        return new DirectorPostgradoLoginService().service(getGenericSession(), getKey());
    }
}