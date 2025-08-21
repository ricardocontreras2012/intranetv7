/*
 * @(#)HelpAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.login;

import service.login.HelpService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonSessionHelp
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class HelpAction extends ActionCommonSupport {

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
        page = new HelpService().service(getGenericSession());
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
