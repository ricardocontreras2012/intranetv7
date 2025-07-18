/*
 * @(#)TitulosyGradosLoginAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.titulosygrados;

import service.titulosygrados.TitulosyGradosLoginService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class TitulosyGradosLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        return new TitulosyGradosLoginService().service(getSesion());
    }
}
