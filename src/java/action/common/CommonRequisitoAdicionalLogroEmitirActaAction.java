/*
 * @(#)CommonRequisitoAdicionalLogroEmitirActaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonRequisitoAdicionalLogroEmitirActaService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonRequisitoAdicionalLogroEmitirActaAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     *
     * @return
     *
     * @throws Exception
     */
    @Override
    public String action() throws Exception {
        return new CommonRequisitoAdicionalLogroEmitirActaService().service(getGenericSession(), getMapParameters(), getKey());
    }
}
