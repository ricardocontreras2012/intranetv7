/*
 * @(#)CommonAdministrativoEmailSetEmailAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonAdministrativoEmailSetEmailService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL
 * CommonAdministrativoEmailSetEmail
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonAdministrativoEmailSetEmailAction extends ActionCommonSupport {

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
        
        return service(this, getGenericSession(), email);
    }    


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
