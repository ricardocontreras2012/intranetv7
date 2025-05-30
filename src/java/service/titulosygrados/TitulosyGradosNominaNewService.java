/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.titulosygrados;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;

/**
 *
 * @author rcontreras
 */
public class TitulosyGradosNominaNewService {
    public static String service(GenericSession genericSession,
           String key)
            throws Exception {

         WorkSession ws = genericSession.getWorkSession(key);
         ws.setExpedienteLogro(null);
         ws.setExpedienteLogroList(null);

         return SUCCESS;
    }
}
