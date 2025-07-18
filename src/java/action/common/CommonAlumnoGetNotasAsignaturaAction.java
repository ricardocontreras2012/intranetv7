/*
 * @(#)CommonAlumnoGetNotasAsignaturaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonAlumnoGetNotasAsignaturaService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonAlumnoGetNotasAsignatura
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonAlumnoGetNotasAsignaturaAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer asignatura;
    private String electiva;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {     
        return new CommonAlumnoGetNotasAsignaturaService().service(getGenericSession(), asignatura, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAsignatura() {
        return asignatura;
    }

    /**
     * Method description
     *
     * @param asignatura
     */
    public void setAsignatura(Integer asignatura) {
        this.asignatura = asignatura;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getElectiva() {
        return electiva;
    }

    /**
     * Method description
     *
     * @param electiva
     */
    public void setElectiva(String electiva) {
        this.electiva = electiva;
    }
}
