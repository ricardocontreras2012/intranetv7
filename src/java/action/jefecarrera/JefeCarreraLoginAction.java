/*
 * @(#)JefeCarreraLoginAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.jefecarrera;

import service.jefecarrera.JefeCarreraLoginService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class JefeCarreraLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {  
        return new JefeCarreraLoginService().service(getGenericSession(), getKey());
    }
}
