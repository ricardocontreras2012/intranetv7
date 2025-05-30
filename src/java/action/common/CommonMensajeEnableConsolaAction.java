/*
 * @(#)CommonMensajeEnableConsolaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */


package action.common;


import infrastructure.support.action.common.ActionCommonSupport;


/**
 * @author Ricardo Contreras S.
 */
public final class CommonMensajeEnableConsolaAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;

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
}
