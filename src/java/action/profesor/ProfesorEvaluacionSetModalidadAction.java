/*
 * @(#)ProfesorEvaluacionSetModalidadAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.profesor;

import service.profesor.ProfesorEvaluacionSetModalidadService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class ProfesorEvaluacionSetModalidadAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String modalidad;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new ProfesorEvaluacionSetModalidadService().service(getGenericSession(), modalidad, getKey());
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getModalidad() {
        return modalidad;
    }

    /**
     * Method description
     *
     *
     * @param modalidad
     */
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
}
