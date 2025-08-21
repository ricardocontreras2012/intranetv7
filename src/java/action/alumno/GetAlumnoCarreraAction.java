/*
 * @(#)GetAlumnoCarreraAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import service.alumno.GetAlumnoCarreraService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class GetAlumnoCarreraAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetAlumnoCarreraService().service(getGenericSession(), getKey());
    }
}
