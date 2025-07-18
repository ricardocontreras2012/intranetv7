/*
 * @(#)SecretariaDocenteLoginAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.secretariadocente;

import service.secretariadocente.SecretariaDocenteLoginService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class SecretariaDocenteLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        return new SecretariaDocenteLoginService().service(getSesion());
    }
}
