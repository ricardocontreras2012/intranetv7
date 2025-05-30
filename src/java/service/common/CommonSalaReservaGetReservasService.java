/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import session.GenericSession;
import session.SecretariaSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class CommonSalaReservaGetReservasService {    
    public static void service(GenericSession genericSession, SecretariaSession secreSession, String key) throws Exception
    {      
         genericSession.getWorkSession(key).setReservaList(ContextUtil.getDAO().getReservaSalaPersistence(ActionUtil.getDBUser()).findReservas(genericSession.getRut()));                         
    }    
}
