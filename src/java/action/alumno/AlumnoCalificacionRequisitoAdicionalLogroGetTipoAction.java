/*
 * @(#)AlumnoCalificacionRequisitoAdicionalLogroGetTipoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import static service.alumno.AlumnoCalificacionRequisitoAdicionalLogroGetTipoService.service;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL
 * AlumnoCalificacionRequisitoAdicionalLogroGetTipo
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoCalificacionRequisitoAdicionalLogroGetTipoAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(this, getGenericSession(), getPos(), getKey());
    }

    /**
     * Valida Parámetro.
     *
     * @return true: Si es válido y false: de lo contrario.
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(),
                getGenericSession().getWorkSession(getKey()).getCalificacionRequisitoAdicionalLogroxInscribirList());
    }
}
