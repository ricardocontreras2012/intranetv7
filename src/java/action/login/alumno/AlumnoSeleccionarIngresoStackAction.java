/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.login.alumno;

import service.login.alumno.AlumnoSeleccionarIngresoStackService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class AlumnoSeleccionarIngresoStackAction extends ActionCommonSupport {
    
    @Override
    public String action() {
        return new AlumnoSeleccionarIngresoStackService().service(getGenericSession(), Manager.getAlumnoSession(sesion), getKey());
    }
}
