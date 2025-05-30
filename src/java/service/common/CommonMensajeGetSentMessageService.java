/*
 * @(#)CommonMensajeGetSentMessageService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMensajeGetSentMessageService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public static String service(GenericSession genericSession, Integer pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        ws.setCurrentMsg(ws.getSentMsgs().get(pos));
        ws.getCurrentMsg().setMensajeAttachList(ContextUtil.getDAO().getMensajeAttachPersistence(ActionUtil.getDBUser()).find(
                        ws.getCurrentMsg().getMsgCorrel()));

        LogUtil.setLog(genericSession.getRut(), ws.getCurrentMsg().getMsgCorrel());

        return SUCCESS;
    }
}
