/*
 * @(#)CommonPersonaGetPersonaxRutAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonPersonaGetPersonaxRutService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL
 * CommonPersonaGetPersonaxRut
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonPersonaGetPersonaxRutAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer rut;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonPersonaGetPersonaxRutService().service(getGenericSession(), getKey(), rut);
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getRut() {
        return rut;
    }

    /**
     * Method description
     *
     * @param rut
     */
    public void setRut(Integer rut) {
        this.rut = rut;
    }
}
