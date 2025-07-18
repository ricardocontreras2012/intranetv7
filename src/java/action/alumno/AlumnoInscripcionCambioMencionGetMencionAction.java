/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import service.alumno.AlumnoInscripcionCambioMencionGetMencionService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Usach
 */
public class AlumnoInscripcionCambioMencionGetMencionAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return new AlumnoInscripcionCambioMencionGetMencionService().service(getGenericSession(), Manager.getAlumnoSession(sesion), getKey());
    }
}
