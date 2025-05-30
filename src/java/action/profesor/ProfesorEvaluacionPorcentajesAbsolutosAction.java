/*
 * @(#)ProfesorEvaluacionPorcentajesAbsolutosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.profesor;

import static service.profesor.ProfesorEvaluacionPorcentajesAbsolutosService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL
 * ProfesorEvaluacionPorcentajesRelativos
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class ProfesorEvaluacionPorcentajesAbsolutosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey());
    }
}
