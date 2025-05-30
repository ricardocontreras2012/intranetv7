
package service.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.ActividadTeletrabajo;
import java.text.ParseException;
import session.GenericSession;
import session.TeleTrabajoSession;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class TeleTrabajoGetTareasService {
    public static String service(GenericSession genericSession, TeleTrabajoSession teleSession, Integer pos, String key) throws ParseException {
        ActividadTeletrabajo actividad = teleSession.getActividadList().get(pos);
        
        teleSession.setActividadTeletrabajo(actividad);
        teleSession.setTareaList(ContextUtil.getDAO().getTareaActividadTeletrabajoPersistence("TT").find(actividad.getId().getAtelRut(), actividad.getId().getAtelFecha()));       
        
        return SUCCESS;
    }    
}
