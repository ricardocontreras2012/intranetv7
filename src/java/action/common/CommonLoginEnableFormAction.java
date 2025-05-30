/*
 * @(#)CommonLoginEnableFormAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonLoginEnableFormService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonLoginEnableForm
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonLoginEnableFormAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        service(getSesion());

        return SUCCESS;
    }
}
