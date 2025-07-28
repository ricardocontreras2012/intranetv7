/*
 * @(#)AlumnoEncuestaDocenteAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import service.alumno.AlumnoEncuestaDocenteService;
import session.Manager;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL AlumnoEncuesta
 *
 * @author Ricardo Contreras S.
 * @version 7, 03/09/2013
 */
public final class AlumnoEncuestaDocenteAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;
    private final AlumnoEncuestaDocenteService svc = new AlumnoEncuestaDocenteService();

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return showForm();
    }

    /**
     * Method description
     *
     * @return
     */
    
    public String search() {   
        return svc.searchService(getGenericSession(), getKey());
    }
    
    public String showForm() {
        return svc.showFormService(getGenericSession(), Manager.getAlumnoSession(sesion), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public String save() {         
        return svc.saveService(getGenericSession(), getMapParameters(), getKey());
    }
}
