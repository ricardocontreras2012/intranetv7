/*
 * @(#)GetActasCarreraAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.acta;

import service.acta.GetActasCarreraService;
import infrastructure.support.action.ActionValidationAgnoSemSupport;

/**
 * Procesa el action mapeado del request a la URL CommonActaGetActasCarrera
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetActasCarreraAction extends ActionValidationAgnoSemSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetActasCarreraService().service(getGenericSession(), getKey(), getPos(), getAgno(), getSem());
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
