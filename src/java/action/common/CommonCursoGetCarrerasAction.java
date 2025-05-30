/*
 * @(#)CommonCursoGetCarrerasAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonCursoGetCarrerasService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonCursoGetCarreras
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonCursoGetCarrerasAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private String actionName;
    private String actionCall;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey());
    }

    /**
     *
     * @return
     */
    public String getActionName() {
        return actionName;
    }

    /**
     *
     * @param actionName
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
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
