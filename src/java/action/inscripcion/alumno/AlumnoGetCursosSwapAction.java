/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.inscripcion.alumno;

import service.inscripcion.alumno.AlumnoGetCursosSwapService;
import infrastructure.support.action.ActionParameterAwareSupport;
import session.Manager;

/**
 *
 * @author Administrador
 */
public class AlumnoGetCursosSwapAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return new AlumnoGetCursosSwapService().service(this, getGenericSession(), Manager.getAlumnoSession(sesion), getMapParameters(), getKey());
    }
}

