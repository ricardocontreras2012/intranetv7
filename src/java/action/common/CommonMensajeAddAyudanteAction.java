/*
 * @(#)CommonMensajeAddAyudanteAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */


package action.common;


import service.common.CommonMensajeAddAyudanteService;
import infrastructure.support.action.common.ActionCommonSupport;


/**
 * Procesa el action mapeado del request a la URL CommonMensajeAddAyudante
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMensajeAddAyudanteAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        String keyAux = getKey();

        setKey(getGenericSession().getWorkSession(getKey()).getKeyParent());

        return new CommonMensajeAddAyudanteService().service(getGenericSession(), getKey(), keyAux);
    }
}
