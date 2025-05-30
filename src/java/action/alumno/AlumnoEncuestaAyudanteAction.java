/*
 * @(#)AlumnoEncuestaAyudanteAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import static service.alumno.AlumnoEncuestaAyudanteService.*;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL AlumnoEncuesta
 *
 * @author Ricardo Contreras S.
 * @version 7, 03/09/2013
 */
public final class AlumnoEncuestaAyudanteAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

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

        return search(getGenericSession(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public String showForm() {
        return showFormServiceAction(getGenericSession(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public String save() {
        return saveServiceAction(getGenericSession(), getMapParameters(), getKey());
    }
}
