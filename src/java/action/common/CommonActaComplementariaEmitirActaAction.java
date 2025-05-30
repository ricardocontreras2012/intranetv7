/*
 * @(#)CommonActaComplementariaEmitirActaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonActaComplementariaEmitirActaService.service;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonActaComplementariaEmitirActaAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(this, getGenericSession(), getMapParameters(), getKey());
    }
}
