/*
 * @(#)ConsultaMisDatosGetMisDatosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.consulta;

import service.consulta.ConsultaMisDatosGetMisDatosService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL ConsultaMisDatosGetMisDatos
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class ConsultaMisDatosGetMisDatosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new ConsultaMisDatosGetMisDatosService().service(getGenericSession());
    }
}
