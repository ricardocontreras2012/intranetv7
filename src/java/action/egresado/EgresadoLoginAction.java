/*
 * @(#)EgresadoLoginAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.egresado;

import static service.egresado.EgresadoLoginService.service;
import infrastructure.support.LoginSessionSupport;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL EgresadoLogin
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class EgresadoLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        LoginSessionSupport loginSessionSupport
                = (LoginSessionSupport) getSesion().get("loginSessionSupport");

        return service(this, getSesion(), loginSessionSupport.getRut(),
                loginSessionSupport.getPassword(), getKey());
    }
}
