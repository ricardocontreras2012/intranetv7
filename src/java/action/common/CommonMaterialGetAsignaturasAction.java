/*
 * @(#)CommonMaterialGetAsignaturasAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */


package action.common;


import service.common.CommonAsignaturaGetAsignaturasService;
import infrastructure.support.action.post.ActionPostCommonSupport;


/**
 * Procesa el action mapeado del request a la URL CommonMaterialGetAsignaturas
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMaterialGetAsignaturasAction extends ActionPostCommonSupport {
    private static final long serialVersionUID = 1L;
    private String            actionCall       = "CommonMaterialGetCursosAsignatura";

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonAsignaturaGetAsignaturasService().service(getGenericSession(), getKey());
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
