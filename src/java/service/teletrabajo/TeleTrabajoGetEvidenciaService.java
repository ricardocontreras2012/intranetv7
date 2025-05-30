/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.TareaActividadTeletrabajoId;
import session.GenericSession;
import session.TeleTrabajoSession;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Javier
 */
public class TeleTrabajoGetEvidenciaService {
    /**
     * Metodo que actualiza la lista de evidencias en TeleTrabajoSession.
     *
     * @param genericSession Sesion de trabajo.
     * @param teleSession
     * @param key LLave para acceder a los datos de la sesion.
     * @param idTarea
     * @return Action status.
     * @throws java.lang.Exception
     */
    public static String service(GenericSession genericSession, TeleTrabajoSession teleSession, String key, TareaActividadTeletrabajoId idTarea) throws Exception{
        //Se actualiza la lista de evidencias en TeleTrabajoSession con las evidencias que tiene actualmente la tarea del id ingresado.
        teleSession.setEvidenciaList(ContextUtil.getDAO().getEvidenciaTarActTeletrabajoPersistence("TT").findByTarea(idTarea.getTatelRut(), idTarea.getTatelFecha(), idTarea.getTatelTarea()));
        return SUCCESS;
    }
}
