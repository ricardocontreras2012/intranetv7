/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author rcontreras
 */
public class CommonPracticaGetSolicitudesxEstadoService {

    /**
     *
     * @param genericSession
     * @param key
     * @param autoridad
     * @param estado
     * @return
     */
    public String service(GenericSession genericSession, String key, Integer autoridad, Integer estado) {           
        genericSession.getWorkSession(key).setSolicitudList(ContextUtil.getDAO().getSolicitudRepository(ActionUtil.getDBUser()).findPracticasxEstado(genericSession.getRut(), autoridad, estado));
        
        return SUCCESS;
    }
}
