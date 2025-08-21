/*
 * @(#)SaveAlumnoEvaluacionAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.evaluacion.profesor;

import service.evaluacion.profesor.ProfesorSaveAlumnoEvaluacionService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL
 * ProfesorEvaluacionSaveAlumnoEvaluacion
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class SaveAlumnoEvaluacionAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new ProfesorSaveAlumnoEvaluacionService().service(getGenericSession(), getMapParameters(), getKey());
    }
}
