/*
 * @(#)CommonMisCursosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonMisCursos
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMisCursosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String actionCall;
    private String title;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return SUCCESS;
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

    /**
     * Method description
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method description
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
