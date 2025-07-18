/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;


import service.common.CommonConvalidacionComisionAddProfService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author rcontreras
 */
public class CommonConvalidacionComisionAddProfAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {

        new CommonConvalidacionComisionAddProfService().service(getGenericSession(), Manager.getSecretariaSession(sesion), getKey());

        setKey(getGenericSession().getWorkSession(getKey()).getKeyParent());

        return SUCCESS;
    }
}

