/*
 * @(#)CommonAyudanteSearchAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonAyudanteSearchService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonAyudanteSearch
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonAyudanteSearchAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String materno;
    private String nombre;
    private String paterno;
    private Integer rut;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), rut, paterno, materno, nombre, getKey());
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }
}
