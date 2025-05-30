/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonSetSemAgnoService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param sem
     * @param agno
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, Integer sem, Integer agno, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        ws.setSemAct(sem);
        ws.setAgnoAct(agno);

        return SUCCESS;
    }
}
