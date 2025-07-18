/*
 * @(#)CommonProfesorCargaHistoricaReporteAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonProfesorGetCargaHistoricaService;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.common.CommonRandomUtil.getKeySession;

/**
 * Procesa el action mapeado del request a la URL
 * CommonProfesorCargaHistoricaReporte
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonProfesorCargaHistoricaReporteAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String actionCall = "CommonReporteGetReportesCurso";

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        String keyParent = getKey();

        setKey(getKeySession());

        return new CommonProfesorGetCargaHistoricaService().service(getGenericSession(), getKey(), keyParent);
    }

    /**
     * Method description
     *
     * @return
     */
    public String getActionCall() {
        return actionCall;
    }

    /**
     * Method description
     *
     * @param actionCall
     */
    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }
}
