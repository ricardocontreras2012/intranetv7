/*
 * @(#)NewEstudioAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.egresado;

import service.egresado.NewEstudioService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Alvaro Romero C.
 */
public class NewEstudioAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new NewEstudioService().service(getGenericSession(), getKey());
    }
}
