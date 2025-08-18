/*
 * @(#)SecretariaDocenteNewSolicitudAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */


package action.convalidacion.secretariadocente;


import service.convalidacion.secretariadocente.SecretariaDocenteNewSolicitudService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;


/**
 *
 * @author Ricardo Contreras S.
 */
public class SecretariaDocenteNewSolicitudAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SecretariaDocenteNewSolicitudService().service(getGenericSession(), Manager.getSecretariaSession(sesion), getKey());
    }
}
