/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.login.vicedecano;

import service.login.vicedecano.ViceDecanoLoginService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class ViceDecanoLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {  
        return new ViceDecanoLoginService().service(getGenericSession(), getKey());
    }
}
