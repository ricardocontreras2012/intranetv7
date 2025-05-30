/*
 * @(#)CommonSessionCloseAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonSessionCloseService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonSessionClose
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonSessionCloseAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String urlRedirect;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        urlRedirect= service(getSesion(), getKey());

        return SUCCESS;
    }

    /**
     *
     * @return
     */
    public String getUrlRedirect() {
        return urlRedirect;
    }
}
