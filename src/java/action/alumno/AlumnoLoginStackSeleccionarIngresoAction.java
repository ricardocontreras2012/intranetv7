/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import static service.alumno.AlumnoLoginStackSeleccionarIngresoService.service;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class AlumnoLoginStackSeleccionarIngresoAction extends ActionCommonSupport {

    @Override
    public String action() {
        return service(getGenericSession(), Manager.getAlumnoSession(sesion), getKey());
    }
}
