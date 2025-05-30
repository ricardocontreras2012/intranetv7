
package service.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.TeleTrabajoSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 *
 * @author rcontreras
 */
public class TeleTrabajoSaveActividadService {

    /**
     *
     * @param action
     * @param genericSession
     * @param teleSession
     * @param descripcion
     * @param fecha
     * @param key
     * @return
     * @throws Exception
     */
    public static String service(ActionCommonSupport action, GenericSession genericSession, TeleTrabajoSession teleSession, String descripcion, String fecha, String key) throws Exception{
        String retVal = SUCCESS;
 
        beginTransaction("TT");
        ContextUtil.getDAO().getActividadTeletrabajoPersistence("TT").insertActividad(teleSession.getFuncionarioTeletrabajo().getFtelRut(),DateUtil.stringToDate(fecha) ,descripcion);
        commitTransaction();
        
        teleSession.setActividadList(ContextUtil.getDAO().getActividadTeletrabajoPersistence("TT").find(genericSession.getRut(), teleSession.getFuncionarioTeletrabajo().getFtelRut()));
        
        return retVal;
    }
}
