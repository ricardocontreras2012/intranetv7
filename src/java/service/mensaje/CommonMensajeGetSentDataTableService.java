/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.mensaje;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Javier
 */
public class CommonMensajeGetSentDataTableService {
    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param start
     * @param length
     * @param searchValue
     * @param tipoOrder
     * @param nombreDataColumnaActual
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key, Integer start, Integer length, String searchValue, String tipoOrder, String nombreDataColumnaActual) {
        genericSession.getWorkSession(key).setSentMsgs(ContextUtil.getDAO().getMensajeRepository(ActionUtil.getDBUser()).find(genericSession.getRut(), start, length, searchValue, tipoOrder, nombreDataColumnaActual));
        genericSession.getWorkSession(key).setCantMsgsSended(ContextUtil.getDAO().getMensajeRepository(ActionUtil.getDBUser()).countMsgs(genericSession.getRut()));
        genericSession.getWorkSession(key).setCantMsgsSendedFiltered(ContextUtil.getDAO().getMensajeRepository(ActionUtil.getDBUser()).countMsgsFiltered(genericSession.getRut(), searchValue));
        return SUCCESS;
    }
}
