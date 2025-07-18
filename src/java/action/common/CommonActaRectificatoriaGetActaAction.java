/*
 * @(#)CommonActaRectificatoriaGetActaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonActaRectificatoriaGetActaService;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request
 *
 * @author Ricardo Contreras S.
 * @version 7, 07/05/2014
 */
public final class CommonActaRectificatoriaGetActaAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonActaRectificatoriaGetActaService().service(getGenericSession(), getPos(), getKey());
    }

    /**
     * Valida Parámetro.
     *
     * @return true: Si es válido y false: de lo contrario.
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getCursoList());
    }
}
