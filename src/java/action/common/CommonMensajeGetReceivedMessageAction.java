/*
 * @(#)CommonMensajeGetReceivedMessageAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonMensajeGetReceivedMessageService;
import infrastructure.support.action.ActionValidationPosSupport;


/**
 * Procesa el action mapeado del request a la URL
 * CommonMensajeGetReceivedMessage
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMensajeGetReceivedMessageAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonMensajeGetReceivedMessageService().service(getGenericSession(), getPos(), getKey());
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
