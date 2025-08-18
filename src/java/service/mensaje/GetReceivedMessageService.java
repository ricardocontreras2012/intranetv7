/*
 * @(#)GetReceivedMessageService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.mensaje;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class GetReceivedMessageService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Integer pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        ws.setCurrentMsg(ws.getReceivedMsgs().get(pos).getMensaje());
        ws.setMensajeFwd(ws.getCurrentMsg());
       
        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getMensajeDestinatarioRepository(ActionUtil.getDBUser()).setReadMessage(ws.getReceivedMsgs().get(pos));
        commitTransaction();
        ws.getCurrentMsg().setMensajeAttachList(ContextUtil.getDAO().getMensajeAttachRepository(ActionUtil.getDBUser()).find(
                        ws.getCurrentMsg().getMsgCorrel()));

        LogUtil.setLog(genericSession.getRut(), ws.getCurrentMsg().getMsgCorrel());

        return SUCCESS;
    }
}
