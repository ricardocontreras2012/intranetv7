/*
 * @(#)CommonAlumnoSearchAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.alumno.CommonAlumnoSearchService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonAlumnoSearch
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonAlumnoSearchAction extends ActionCommonSupport {

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
        return new CommonAlumnoSearchService().service(getGenericSession(), rut, paterno, materno, nombre, getKey());
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
