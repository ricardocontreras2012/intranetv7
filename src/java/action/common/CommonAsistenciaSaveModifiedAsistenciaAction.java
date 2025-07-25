/*
 * @(#)CommonAsistenciaSaveModifiedAsistenciaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonAsistenciaSaveModifiedAsistenciaService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL
 * CommonAsistenciaSaveModifiedAsistencia
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonAsistenciaSaveModifiedAsistenciaAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;
    private String fechaAsistencia;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {               
        return new CommonAsistenciaSaveModifiedAsistenciaService().service(getGenericSession(), getMapParameters(), getKey(),
                fechaAsistencia);
    }

    /**
     * Method description
     *
     * @param fechaAsistencia
     */
    public void setFechaAsistencia(String fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }
}
