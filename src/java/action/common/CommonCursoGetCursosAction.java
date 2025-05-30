/*
 * @(#)CommonCursoGetCursosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonCursoGetCursosService.service;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.common.CommonRandomUtil.getKeySession;

/**
 * Procesa el action mapeado del request a la URL CommonCursoGetCursosActualesxAgnoSem
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonCursoGetCursosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String actionCall;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        String keyParent = getKey();
        setKey(getKeySession());

        return service(getGenericSession(), getKey(), keyParent, actionCall);
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
