/*
 * @(#)CommonRequisitoAdicionalLogroGetListaxCalificarAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonRequisitoAdicionalLogroGetListaxCalificarService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL
 * CommonRequisitoAdicionalLogroGetListaxCalificar
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonRequisitoAdicionalLogroGetListaxCalificarAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer trequisitoLogroAdicional;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonRequisitoAdicionalLogroGetListaxCalificarService().service(getGenericSession(),
                trequisitoLogroAdicional, getKey());
    }

    /**
     *
     * @return
     */
    public Integer getTrequisitoLogroAdicional() {
        return trequisitoLogroAdicional;
    }

    /**
     *
     * @param trequisitoLogroAdicional
     */
    public void setTrequisitoLogroAdicional(Integer trequisitoLogroAdicional) {
        this.trequisitoLogroAdicional = trequisitoLogroAdicional;
    }
}
