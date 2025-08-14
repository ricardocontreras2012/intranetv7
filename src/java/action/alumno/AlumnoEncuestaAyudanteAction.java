/*
 * @(#)AlumnoEncuestaAyudanteAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import infrastructure.support.action.ActionParameterAwareSupport;
import service.encuesta.alumno.AlumnoEncuestaAyudanteService;

/**
 * Procesa el action mapeado del request a la URL AlumnoEncuesta
 *
 * @author Ricardo Contreras S.
 * @version 7, 03/09/2013
 */
public final class AlumnoEncuestaAyudanteAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;
    private final AlumnoEncuestaAyudanteService svc = new AlumnoEncuestaAyudanteService();

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
