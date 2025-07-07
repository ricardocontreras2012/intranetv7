/*
 * @(#)AlumnoEncuestaDocenteAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import static service.alumno.AlumnoEncuestaDocenteService.searchService;
import static service.alumno.AlumnoEncuestaDocenteService.saveService;
import static service.alumno.AlumnoEncuestaDocenteService.showFormService;
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
        return searchService(getGenericSession(), getKey());
    }
    
    public String showForm() {
        return showFormService(getGenericSession(), Manager.getAlumnoSession(sesion), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public String save() {
        
System.out.println("ok 1111111111111");         
        return saveService(getGenericSession(), getMapParameters(), getKey());
    }
}
