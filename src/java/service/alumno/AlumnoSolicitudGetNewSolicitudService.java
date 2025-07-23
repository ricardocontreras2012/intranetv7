/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author rcontreras
 */
public class AlumnoSolicitudGetNewSolicitudService {
/**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        ws.setTsolicitudList(ContextUtil.getDAO().getTsolicitudRepository(ActionUtil.getDBUser()).find(ws.getAluCar()));

        return SUCCESS;
    }
}
