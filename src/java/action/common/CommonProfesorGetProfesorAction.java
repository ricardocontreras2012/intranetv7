/*
 * @(#)CommonProfesorGetProfesorAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonProfesorGetProfesorService;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL CommonProfesorGetProfesor
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonProfesorGetProfesorAction extends ActionValidationPosSupport {

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
        return new CommonProfesorGetProfesorService().service(getGenericSession(), getPos(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getProfesorList());
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
