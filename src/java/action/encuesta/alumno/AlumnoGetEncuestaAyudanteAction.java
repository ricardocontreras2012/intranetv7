/*
 * @(#)AlumnoGetEncuestaAyudanteAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.encuesta.alumno;

import infrastructure.support.action.ActionParameterAwareSupport;
import service.encuesta.alumno.AlumnoGetEncuestaAyudanteService;

/**
 * Procesa el action mapeado del request a la URL AlumnoEncuesta
 *
 * @author Ricardo Contreras S.
 * @version 7, 03/09/2013
 */
public final class AlumnoGetEncuestaAyudanteAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;
    private final AlumnoGetEncuestaAyudanteService svc = new AlumnoGetEncuestaAyudanteService();

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
        return svc.showFormServiceAction(getGenericSession(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public String save() {
        return svc.saveServiceAction(getGenericSession(), getMapParameters(), getKey());
    }
}
