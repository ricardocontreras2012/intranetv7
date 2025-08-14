/*
 * @(#)ConsultaMisDatosSaveMisDatosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.consulta;

import service.misdatos.consulta.ConsultaMisDatosSaveMisDatosService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL ConsultaMisDatosSaveMisDatos
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class ConsultaMisDatosSaveMisDatosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String email;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new ConsultaMisDatosSaveMisDatosService().service(this, getGenericSession(), email);
    }

    /**
     * Method description
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
