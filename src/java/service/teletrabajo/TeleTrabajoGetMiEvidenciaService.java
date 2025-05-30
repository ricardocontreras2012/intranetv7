/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.TareaActividadTeletrabajo;
import domain.model.TareaActividadTeletrabajoId;
import java.util.Date;
import session.GenericSession;
import session.TeleTrabajoSession;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 *
 * @author Javier
 */
public class TeleTrabajoGetMiEvidenciaService {
    /**
     * Metodo que actualiza la lista de evidencias en TeleTrabajoSession.
     * Tambien realiza el cambio al estado de la tarea y el de la actividad, 
     * cambiando de "con evidencia" y "sin evidencia" en el caso de tarea;
     * y de "evidenciado" y "aceptado" en el caso de actividad.
     *
     * @param genericSession Sesion de trabajo.
     * @param teleSession
     * @param key LLave para acceder a los datos de la sesion.
     * @param idTarea
     * @return Action status.
     * @throws java.lang.Exception
     */
    public static String service(GenericSession genericSession, TeleTrabajoSession teleSession, String key, TareaActividadTeletrabajoId idTarea) throws Exception{
        //Se crean las variables que se usaran a lo largo de la funcion.
        Integer rut = idTarea.getTatelRut();
        Date fecha = idTarea.getTatelFecha();
        Integer correlTarea = idTarea.getTatelTarea();
        TareaActividadTeletrabajo tareaActividadTeletrabajo = ContextUtil.getDAO().getTareaActividadTeletrabajoPersistence("TT").find(rut, fecha, correlTarea);
        
        //Se actualiza la lista de evidencias en TeleTrabajoSession con las evidencias que tiene actualmente la tarea del id ingresado.
        teleSession.setEvidenciaList(ContextUtil.getDAO().getEvidenciaTarActTeletrabajoPersistence("TT").findByTarea(rut, fecha, correlTarea));
        
        //Se actualizan los estados de actividad y tarea dependiendo de si la lista de evidencias esta vacia o no.
        if(teleSession.getEvidenciaList().isEmpty())
        {
            tareaActividadTeletrabajo.setTatelEvidencia("N");
            
            boolean isEvidenced = false;
            for(TareaActividadTeletrabajo tarea : teleSession.getTareaList()){
                if("S".equals(tarea.getTatelEvidencia())){
                    isEvidenced = true;
                    break;
                }
            }

            if(!isEvidenced){
                teleSession.getActividadTeletrabajo().setAtelEstado("A");
            }
            
            beginTransaction("TT");
            ContextUtil.getDAO().getActividadTeletrabajoPersistence("TT").update(teleSession.getActividadTeletrabajo());
            ContextUtil.getDAO().getTareaActividadTeletrabajoPersistence("TT").update(tareaActividadTeletrabajo);
            commitTransaction();
            /*
            //La lista esta vacia
            tareaActividadTeletrabajo.getActividadTeletrabajo().setAtelEstado("A");
            tareaActividadTeletrabajo.setTatelEvidencia("N");
            
            beginTransaction("TT");
            ContextUtil.getDAO().getActividadTeletrabajoPersistence("TT").update(tareaActividadTeletrabajo.getActividadTeletrabajo());
            ContextUtil.getDAO().getTareaActividadTeletrabajoPersistence("TT").update(tareaActividadTeletrabajo);
            commitTransaction();
            */
        }
        else
        {
            if(!tareaActividadTeletrabajo.getActividadTeletrabajo().getAtelEstado().equals("P"))
            {
                //La lista tiene evidencias
                tareaActividadTeletrabajo.getActividadTeletrabajo().setAtelEstado("E");
                tareaActividadTeletrabajo.setTatelEvidencia("S");

                beginTransaction("TT");
                ContextUtil.getDAO().getActividadTeletrabajoPersistence("TT").update(tareaActividadTeletrabajo.getActividadTeletrabajo());
                ContextUtil.getDAO().getTareaActividadTeletrabajoPersistence("TT").update(tareaActividadTeletrabajo);
                commitTransaction();
            }
        }
        return SUCCESS;
    }
}
