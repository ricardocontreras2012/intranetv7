/*
 * @(#)CommonSessionHelpAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonSessionHelpService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonSessionHelp
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonSessionHelpAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String page;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        page = new CommonSessionHelpService().service(getGenericSession());
        return SUCCESS;
    }

    /**
     *
     * @return
     */
    public String getPage() {
        return page;
    }
}
