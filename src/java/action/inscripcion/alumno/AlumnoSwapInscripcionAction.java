/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.inscripcion.alumno;

import service.inscripcion.alumno.AlumnoInscripcionSwapInscripcionService;
import infrastructure.support.action.ActionValidationPosSupport;
import session.Manager;

/**
 *
 * @author Administrador
 */
public class AlumnoSwapInscripcionAction extends  ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){
        return new AlumnoInscripcionSwapInscripcionService().service(getGenericSession(), Manager.getAlumnoSession(sesion), getPos(),getKey());
    }
/**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getCursoList());
    }
}

