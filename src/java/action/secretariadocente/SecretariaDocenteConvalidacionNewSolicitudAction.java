/*
 * @(#)SecretariaDocenteConvalidacionNewSolicitudAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */


package action.secretariadocente;


import service.secretariadocente.SecretariaDocenteConvalidacionNewSolicitudService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;


/**
 *
 * @author Ricardo Contreras S.
 */
public class SecretariaDocenteConvalidacionNewSolicitudAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SecretariaDocenteConvalidacionNewSolicitudService().service(getGenericSession(), Manager.getSecretariaSession(sesion), getKey());
    }
}
