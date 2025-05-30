/*
 * @(#)CommonMensajeRemoveReceivedMessagesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import domain.repository.MensajeDestinatarioPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMensajeRemoveReceivedMessagesService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @param start
     * @param length
     * @param searchValue
     * @param tipoOrder
     * @param nombreDataColumnaActual
     * @return Action status.
     */
    public static String service(GenericSession genericSession, Map<String, String[]> parameters, String key, int start, int length, String searchValue, String tipoOrder, String nombreDataColumnaActual) {
        WorkSession ws = genericSession.getWorkSession(key);
        MensajeDestinatarioPersistence mensajeDestinatarioPersistence = ContextUtil.getDAO().getMensajeDestinatarioPersistence(ActionUtil.getDBUser());

        AtomicInteger index = new AtomicInteger(0);
        beginTransaction(ActionUtil.getDBUser());

        ws.getReceivedMsgs().stream()
                .filter(msg -> parameters.get("ck_" + index.getAndIncrement()) != null)
                .forEach(msg -> {
                    LogUtil.setLog(genericSession.getRut(), msg.getMensaje().getMsgCorrel());
                    mensajeDestinatarioPersistence.setDeleteReceivedMessage(msg);
                });

        commitTransaction();

        ws.setReceivedMsgs(mensajeDestinatarioPersistence.findReceivedWithLimits(genericSession.getRut(), start, length, searchValue, tipoOrder, nombreDataColumnaActual));

        return SUCCESS;
    }
}
