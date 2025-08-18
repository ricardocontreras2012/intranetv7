/*
 * @(#)AlumnoGetCursosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.inscripcion.alumno;

import service.inscripcion.alumno.AlumnoInscripcionGetCursosService;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL AlumnoInscripcionGetCursos
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoGetCursosAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){
        return new AlumnoInscripcionGetCursosService().service(getGenericSession(), Manager.getAlumnoSession(sesion), getPos(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getAluCar().getDerechosInscripcion());
    }
}
