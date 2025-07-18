/*
 * @(#)CommonInscripcionEnableInscripcionAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;


import service.common.CommonInscripcionEnableInscripcionService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonInscripcionEnableInscripcionAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonInscripcionEnableInscripcionService().service(getGenericSession(), getKey());
    }
}
