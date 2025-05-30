/*
 * @(#)ApoyoLoginAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.apoyo;

import static service.apoyo.ApoyoLoginService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL ApoyoLogin
 *
 * @author Ricardo Contreras S.
 * @version 7, 07/05/2019
 */
public final class ApoyoLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        return service(getSesion(), getKey());
    }
}
