/*
 * @(#)ProfesorActaEmitirActaNumericaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.profesor;

import service.profesor.ProfesorActaEmitirActaNumericaService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL ProfesorActaEmitirActaNumerica
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class ProfesorActaEmitirActaNumericaAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new ProfesorActaEmitirActaNumericaService().service(getGenericSession(), getMapParameters(), getKey());
    }
}
