/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Usach
 */
public class CommonRCurricularGetMencionService {
    
    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return
     */
    public String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        ws.setMencionList(ContextUtil.getDAO().getMencionPersistence(ActionUtil.getDBUser()).findAll());
        ws.setUnidadList(ContextUtil.getDAO().getUnidadPersistence(ActionUtil.getDBUser()).findAll());
        return SUCCESS;
    }
}
