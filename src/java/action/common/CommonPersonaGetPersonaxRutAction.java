/*
 * @(#)CommonPersonaGetPersonaxRutAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import domain.model.Persona;
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
    private Persona persona;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        persona =  new CommonPersonaGetPersonaxRutService().service(getGenericSession(), getKey(), rut);
        return SUCCESS;
    }

    /**
     * Method description
     *
     * @param rut
     */
    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public Persona getPersona() {
        return persona;
    }
}
