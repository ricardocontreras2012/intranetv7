/*
 * @(#)AlumnoSeleccionarIngresoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.login.alumno;

import service.login.alumno.AlumnoSeleccionarIngresoService;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL AlumnoLoginSeleccionarIngreso
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoSeleccionarIngresoAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return new AlumnoSeleccionarIngresoService().service(getGenericSession(), Manager.getAlumnoSession(sesion), getPos(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getAluCarList());
    }
}
