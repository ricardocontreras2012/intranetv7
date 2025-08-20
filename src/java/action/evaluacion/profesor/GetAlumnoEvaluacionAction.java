/*
 * @(#)GetAlumnoEvaluacionAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.evaluacion.profesor;

import infrastructure.support.action.ActionValidationPosSupport;
import service.evaluacion.profesor.ProfesorGetAlumnoEvaluacionService;

/**
 * Procesa el action mapeado del request a la URL
 * ProfesorEvaluacionGetAlumnoEvaluacion
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetAlumnoEvaluacionAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new ProfesorGetAlumnoEvaluacionService().service(getGenericSession(), getPos(), getKey());
    }
    
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getEvaluacionList());
    }
}
