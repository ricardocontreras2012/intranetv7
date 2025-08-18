/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Virtual
 */
public class CommonEmpleadorGetEmpleadoresxNombreService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param nombre
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key, String nombre) {
//        genericSession.getWorkSession(key).setEmpleador(
//                ContextUtil.getDAO().getEmpleadorRepository(AppStaticsUtil.USER_TYPE_COMMON).find(rut));
        WorkSession ws = genericSession.getWorkSession(key);        
        ws.setEmpleadorList(ContextUtil.getDAO().getEmpleadorRepository("EG").find(nombre));

        return SUCCESS;
    }
}
