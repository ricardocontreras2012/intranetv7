/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 *
 * @author Administrador
 */
public class CommonAlumnoProgramaEstudioGetCalificacionesService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        ws.setCalificaciones(ContextUtil.getDAO().getCalificacionRepository(genericSession.getUserType()).findCalProgramas(ws.getAluCar()));
        LogUtil.setLog(genericSession.getRut(), ws.getAluCar().getId().getAcaRut());

        return SUCCESS;
    }

}
