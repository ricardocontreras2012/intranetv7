/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.ActividadTeletrabajo;
import session.GenericSession;
import session.TeleTrabajoSession;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 *
 * @author Javier
 */
public class TeleTrabajoEnviarRevisionMiActividadService {
    
    /**
     * Metodo que cambia el estado de una actividad a "Pendiente" si es que 
     * este esta en "Evidenciado".
     *
     * @param genericSession Sesion de trabajo.
     * @param teleSession
     * @param key LLave para acceder a los datos de la sesion.
     * @param pos
     * @return Action status.
     * @throws java.lang.Exception
     */
    public static String service(GenericSession genericSession, TeleTrabajoSession teleSession, String key, Integer pos) throws Exception
    {
        ActividadTeletrabajo actividadTeletrabajo = teleSession.getActividadList().get(pos);
        
        if(actividadTeletrabajo.getAtelEstado().equals("E"))
        {
            actividadTeletrabajo.setAtelEstado("P");
            
            beginTransaction("TT");
            ContextUtil.getDAO().getActividadTeletrabajoPersistence("TT").update(actividadTeletrabajo);
            commitTransaction();
        }
        
        return SUCCESS;
    }
}
