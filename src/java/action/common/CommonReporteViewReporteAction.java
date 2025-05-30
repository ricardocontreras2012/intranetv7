/*
 * @(#)CommonReporteViewReporteAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.profesor.ProfesorReporteModifyReporteService.service;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL CommonReporteViewReporte
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonReporteViewReporteAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;
    private Integer sesionReporte;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), sesionReporte, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(sesionReporte, getGenericSession().getWorkSession(getKey()).getReportes());
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getSesionReporte() {
        return sesionReporte;
    }

    /**
     * Method description
     *
     * @param sesionReporte
     */
    public void setSesionReporte(Integer sesionReporte) {
        this.sesionReporte = sesionReporte;
    }
}
