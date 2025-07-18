/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import com.opensymphony.xwork2.Action;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.common.CommonUtil;

/**
 *
 * @author Administrador
 */
public class CommonAgnoSemService {
    
    public String service(GenericSession genericSession, String actionCall, String key) {
        
        WorkSession ws = genericSession.getWorkSession(key);
        
        if (ws.getAgnoAct() == null || ws.getSemAct() == null) {
            CommonUtil.setAgnoSemAct(ws);
        }
        ws.setActionCall(actionCall);
        
        return Action.SUCCESS;
    }
}
