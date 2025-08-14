
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.mantenedor.registradorcurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 *
 * @author Usach
 */
public class RegistradorCurricularMencionGetService {

    public String service(GenericSession genericSession, String key) {         
        WorkSession ws = genericSession.getWorkSession(key);
        ws.setMencionList(ContextUtil.getDAO().getMencionRepository(ActionUtil.getDBUser()).find(genericSession.getUserType(), genericSession.getRut()));

        LogUtil.setLog(genericSession.getRut());
        return SUCCESS;
    }
}
