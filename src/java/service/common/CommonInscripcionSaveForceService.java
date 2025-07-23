/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Usach
 */
public class CommonInscripcionSaveForceService {
    public String service(GenericSession genericSession, Integer pos, String force, 
            String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();

        ContextUtil.getDAO().getInscripcionRepository(ActionUtil.getDBUser()).setForce(aluCar.getId(), aluCar.getInsList().get(pos).getId(), force);
     
        return SUCCESS;
    }    
}
