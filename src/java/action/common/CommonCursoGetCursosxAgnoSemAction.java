/*
 * @(#)CommonCursoGetCursosxAgnoSemAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonCursoGetCursosxAgnoSemService.service;
import infrastructure.support.action.ActionValidationAgnoSemSupport;

/**
 * Procesa el action mapeado del request a la URL CommonCursoGetCursosxAgnoSem
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonCursoGetCursosxAgnoSemAction extends ActionValidationAgnoSemSupport {

    private static final long serialVersionUID = 1L;
    private String actionCall;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey(), getPos(), getAgno(), getSem(), actionCall);
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

    /**
     *
     * @return
     */
    public String getActionCall() {
        return actionCall;
    }

    /**
     *
     * @param actionCall
     */
    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }
}
