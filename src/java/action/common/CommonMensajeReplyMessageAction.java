/*
 * @(#)CommonMensajeReplyMessageAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonMensajeReplyMessageService;
import infrastructure.support.action.post.ActionPostValidationSupport;

/**
 * Procesa el action mapeado del request a la URL CommonMensajeReplyMessage
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMensajeReplyMessageAction extends ActionPostValidationSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonMensajeReplyMessageService().service(getGenericSession(), getPos(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getReceivedMsgs());
    }
}
