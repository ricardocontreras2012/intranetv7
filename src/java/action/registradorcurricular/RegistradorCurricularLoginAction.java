/*
 * @(#)RegistradorCurricularLoginAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.registradorcurricular;

import service.login.registradorcurricular.RegistradorCurricularLoginService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class RegistradorCurricularLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        return new RegistradorCurricularLoginService().service(getSesion(), getKey());
    }
}
