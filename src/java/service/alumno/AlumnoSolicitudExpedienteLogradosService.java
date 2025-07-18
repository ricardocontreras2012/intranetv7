/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import session.GenericSession;
import session.WorkSession;

/**
 *
 * @author Ricardo
 */
public class AlumnoSolicitudExpedienteLogradosService {

    public String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        ws.setExpedienteLogroList(ContextUtil.getDAO().getExpedienteLogroPersistence(ActionUtil.getDBUser()).findGeneradas(ws.getAluCar()));
        
        return SUCCESS;
    }
}
