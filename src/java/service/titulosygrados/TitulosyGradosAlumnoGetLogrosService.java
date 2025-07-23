/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.titulosygrados;

import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import session.GenericSession;
import session.WorkSession;

/**
 *
 * @author Ricardo
 */
public class TitulosyGradosAlumnoGetLogrosService {

    public String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        ws.setExpedienteLogroList(ContextUtil.getDAO().getExpedienteLogroRepository(ActionUtil.getDBUser()).find(ws.getAluCar()));

        LogUtil.setLog(genericSession.getRut(), ws.getAluCar().getId().getAcaRut());
        return SUCCESS;
    }
}
