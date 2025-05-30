/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.EvidenciaTareaActTeletrabajo;
import domain.model.TareaActividadTeletrabajo;
import session.GenericSession;
import session.TeleTrabajoSession;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;

/**
 *
 * @author Javier
 */
public class TeleTrabajoDeleteEvidenciaService {
    /**
     * Metodo que elimina una evidencia.
     *
     * @param genericSession Sesion de trabajo.
     * @param teleSession
     * @param key LLave para acceder a los datos de la sesion.
     * @param correl
     * @param posTarea
     * @return 
     */
    public static String service(GenericSession genericSession, TeleTrabajoSession teleSession, String key, Integer correl, Integer posTarea) throws Exception{
        //Se crean las variables a utilizar
        TareaActividadTeletrabajo tareaActividadTeletrabajo = teleSession.getTareaList().get(posTarea);
        EvidenciaTareaActTeletrabajo evidenciaTareaActTeletrabajo = ContextUtil.getDAO().getEvidenciaTarActTeletrabajoPersistence("TT").find(tareaActividadTeletrabajo.getId().getTatelRut(), tareaActividadTeletrabajo.getId().getTatelFecha(), tareaActividadTeletrabajo.getId().getTatelTarea(), correl);
        
        //Se ejecuta la eliminación de la evidencia
        beginTransaction("TT");
        ContextUtil.getDAO().getEvidenciaTarActTeletrabajoPersistence("TT").deleteEvidencia(evidenciaTareaActTeletrabajo.getId());
        commitTransaction();
        
        LogUtil.setLog(genericSession.getRut());
        return SUCCESS;
    }
}
