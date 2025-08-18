/*
 * @(#)SecretariaDocenteGenerarActaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.convalidacion.secretariadocente;

import service.convalidacion.secretariadocente.SecretariaDocenteGenerarActaService;
import session.Manager;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class SecretariaDocenteGenerarActaAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SecretariaDocenteGenerarActaService().service(this, getGenericSession(), Manager.getSecretariaSession(sesion), getMapParameters(), getKey());
    }
}
