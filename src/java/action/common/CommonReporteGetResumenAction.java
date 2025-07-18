/*
 * @(#)CommonReporteGetResumenAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonReporteGetResumenService;
import infrastructure.support.action.common.ActionCommonAgnoSemSupport;

/**
 * Procesa el action mapeado del request a la URL CommonReporteGetResumen
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonReporteGetResumenAction extends ActionCommonAgnoSemSupport {

    private static final long serialVersionUID = 1L;
    private Integer pos;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonReporteGetResumenService().service(getGenericSession(), getAgno(), getSem(), pos, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getPos() {
        return pos;
    }

    /**
     * Method description
     *
     * @param pos
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }
}
