/*
 * @(#)CommonMensajeGetNumMessagesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonMensajeGetNumMessagesService.service;
import infrastructure.support.action.post.ActionPostCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonMensajeGetNumMessages
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMensajeGetNumMessagesAction extends ActionPostCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey());
    }
}
