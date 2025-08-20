/*
 * @(#)EnableInscripcionAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.inscripcion;


import service.inscripcion.EnableInscripcionService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class EnableInscripcionAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new EnableInscripcionService().service(getGenericSession(), getKey());
    }
}
