/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.reservasala;

import session.GenericSession;
import session.SecretariaSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class GetReservasService {    
    public void service(GenericSession genericSession, SecretariaSession secreSession, String key) throws Exception
    {      
         genericSession.getWorkSession(key).setReservaList(ContextUtil.getDAO().getReservaSalaRepository(ActionUtil.getDBUser()).findReservas(genericSession.getRut()));                         
    }    
}
