/*
 * @(#)CommonRequisitoAdicionalLogroEmitirActaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonRequisitoAdicionalLogroEmitirActaService.service;
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
        return service(getGenericSession(), getMapParameters(), getKey());
    }
}
