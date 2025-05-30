/*
 * @(#)JefeAreaLoginAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.jefearea;

import static service.jefearea.JefeAreaLoginService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class JefeAreaLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey());
    }
}
