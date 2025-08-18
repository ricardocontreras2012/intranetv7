/*
 * @(#)GetNumMessagesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.mensaje;

import service.mensaje.GetNumMessagesService;
import infrastructure.support.action.post.ActionPostCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonMensajeGetNumMessages
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetNumMessagesAction extends ActionPostCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetNumMessagesService().service(getGenericSession(), getKey());
    }
}
