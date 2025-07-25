/*
 * @(#)CommonAlumnoGetNotasAdicionalAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonAlumnoGetNotasAdicionalService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonAlumnoGetNotasAdicional
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonAlumnoGetNotasAdicionalAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer adicional;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonAlumnoGetNotasAdicionalService().service(getGenericSession(), adicional, getKey());
    }    

    /**
     * Method description
     *
     * @param adicional
     */
    public void setAdicional(Integer adicional) {
        this.adicional = adicional;
    }
}
