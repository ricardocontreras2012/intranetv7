/*
 * @(#)GetIframeContentAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.login;

import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonLoginIframeContent
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetIframeContentAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        String retValue = "common";

        if ("AL".equals(getGenericSession().getUserType())) {
            retValue = "alumno";
        } else {
            if ("PR".equals(getGenericSession().getUserType()) || getGenericSession().isAutoridad()) {
                retValue = "profesor";
            } else {
                if ("EG".equals(getGenericSession().getUserType())) {
                    retValue = "egresado";
                }
            }
        }

        return retValue;
    }
}
