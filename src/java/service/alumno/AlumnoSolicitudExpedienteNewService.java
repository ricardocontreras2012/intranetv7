/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;

/**
 *
 * @author Ricardo
 */
public class AlumnoSolicitudExpedienteNewService {
     public static String service(GenericSession genericSession, int pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        ws.setExpedienteLogro(ws.getExpedienteLogroList().get(pos));
        return SUCCESS;
    }
}
