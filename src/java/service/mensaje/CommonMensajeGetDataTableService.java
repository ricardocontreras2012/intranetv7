package service.mensaje;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Javier
 */
public final class CommonMensajeGetDataTableService {
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
    public String service(GenericSession genericSession, String key, int start, int length, String searchValue, String tipoOrder, String nombreDataColumnaActual) {
        genericSession.getWorkSession(key).setReceivedMsgs(ContextUtil.getDAO().getMensajeDestinatarioRepository(ActionUtil.getDBUser()).findReceivedWithLimits(genericSession.getRut(), start, length, searchValue, tipoOrder, nombreDataColumnaActual));
        genericSession.getWorkSession(key).setCantMsgsReceived(ContextUtil.getDAO().getMensajeDestinatarioRepository(ActionUtil.getDBUser()).countMsgsReceived(genericSession.getRut()));
        genericSession.getWorkSession(key).setCantMsgsReceivedFiltered(ContextUtil.getDAO().getMensajeDestinatarioRepository(ActionUtil.getDBUser()).countMsgsReceivedFiltered(genericSession.getRut(), searchValue));
        return SUCCESS;
    }
}
