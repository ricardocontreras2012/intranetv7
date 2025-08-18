/*
 * @(#)EmitirActaComplementariaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.acta;

import service.acta.EmitirActaComplementariaService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class EmitirActaComplementariaAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new EmitirActaComplementariaService().service(this, getGenericSession(), getMapParameters(), getKey());
    }
}
