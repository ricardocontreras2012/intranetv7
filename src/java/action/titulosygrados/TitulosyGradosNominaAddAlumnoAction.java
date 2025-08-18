/*
 * @(#)TitulosyGradosNominaAddAlumnoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.titulosygrados;

import service.nominatitulacion.titulosygrados.TitulosyGradosAddAlumnoService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class TitulosyGradosNominaAddAlumnoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;


    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new TitulosyGradosAddAlumnoService().service(getGenericSession(), getKey());
    }

}
