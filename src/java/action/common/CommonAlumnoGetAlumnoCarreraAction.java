/*
 * @(#)CommonAlumnoGetAlumnoCarreraAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.alumno.CommonAlumnoGetAlumnoCarreraService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonAlumnoGetAlumnoCarreraAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonAlumnoGetAlumnoCarreraService().service(getGenericSession(), getKey());
    }
}
