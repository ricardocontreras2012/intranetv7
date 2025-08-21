/*
 * @(#)GetAyudanteAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.ayudante;

import service.ayudante.GetAyudanteService;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL CommonAyudanteGetAyudante
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetAyudanteAction extends ActionValidationPosSupport {

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
        actionCall = getGenericSession().getWorkSession(getKey()).getActionCall();
        return new GetAyudanteService().service(getGenericSession(), getPos(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getAyudanteList());
    }

    /**
     * Method description
     *
     * @return
     */
    public String getActionCall() {
        return actionCall;
    }

    /**
     * Method description
     *
     * @param actionCall
     */
    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }
}
