/*
 * @(#)CommonActaGetActasCarreraAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.acta.CommonActaGetActasCarreraService;
import infrastructure.support.action.ActionValidationAgnoSemSupport;

/**
 * Procesa el action mapeado del request a la URL CommonActaGetActasCarrera
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonActaGetActasCarreraAction extends ActionValidationAgnoSemSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonActaGetActasCarreraService().service(getGenericSession(), getKey(), getPos(), getAgno(), getSem());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getMiCarreraSupportList());
    }
}
