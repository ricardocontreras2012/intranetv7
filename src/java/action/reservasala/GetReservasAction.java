/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.reservasala;


import service.reservasala.GetReservasService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class GetReservasAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    @Override
    public String action() throws Exception{
        new GetReservasService().service(getGenericSession(), Manager.getSecretariaSession(sesion),getKey());
        return SUCCESS;
    }
}
