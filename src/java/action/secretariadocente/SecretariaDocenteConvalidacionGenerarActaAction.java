/*
 * @(#)SecretariaDocenteConvalidacionGenerarActaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.secretariadocente;

import service.convalidacion.secretariadocente.SecretariaDocenteConvalidacionGenerarActaService;
import session.Manager;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class SecretariaDocenteConvalidacionGenerarActaAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SecretariaDocenteConvalidacionGenerarActaService().service(this, getGenericSession(), Manager.getSecretariaSession(sesion), getMapParameters(), getKey());
    }
}
