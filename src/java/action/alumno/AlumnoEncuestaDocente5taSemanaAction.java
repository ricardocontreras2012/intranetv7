/*
 * @(#)AlumnoEncuestaDocente5taSemanaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import service.encuesta.alumno.AlumnoEncuestaDocente5taSemanaService;
import infrastructure.support.action.ActionParameterAwareSupport;
import session.Manager;

/**
 * Procesa el action mapeado del request a la URL AlumnoEncuesta
 *
 * @author Ricardo Contreras S.
 * @version 7, 03/09/2013
 */
public final class AlumnoEncuestaDocente5taSemanaAction extends ActionParameterAwareSupport {
    private static final long serialVersionUID = 1L;
    private AlumnoEncuestaDocente5taSemanaService svc = new AlumnoEncuestaDocente5taSemanaService();

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return showForm();
    }
    
    public String searchEncuesta() {
        return svc.search(getGenericSession(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public String showForm() {
        return svc.showFormService(getGenericSession(), Manager.getAlumnoSession(sesion), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public String save() {
        return svc.saveService(getGenericSession(), getMapParameters(), Manager.getAlumnoSession(sesion), getKey());
    }
}
