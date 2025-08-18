/*
 * @(#)RemoveReceivedMessagesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.mensaje;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Mensaje;
import java.util.Map;
import domain.repository.MensajeDestinatarioRepository;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import java.util.stream.IntStream;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class RemoveReceivedMessagesService {

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
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key, int start, int length, String searchValue, String tipoOrder, String nombreDataColumnaActual) {
        WorkSession ws = genericSession.getWorkSession(key);
        MensajeDestinatarioRepository mensajeDestinatarioRepo = ContextUtil.getDAO().getMensajeDestinatarioRepository(ActionUtil.getDBUser());

        beginTransaction(ActionUtil.getDBUser());

        IntStream.range(0, ws.getReceivedMsgs().size())
                .filter(i -> parameters.get("ck_" + i) != null)
                .forEach(i -> {
                    Mensaje msg = ws.getReceivedMsgs().get(i).getMensaje();
                    LogUtil.setLog(genericSession.getRut(), msg.getMsgCorrel());
                    mensajeDestinatarioRepo.setDeleteReceivedMessage(ws.getReceivedMsgs().get(i));
                });

        commitTransaction();            
        ws.setReceivedMsgs(mensajeDestinatarioRepo.findReceivedWithLimits(genericSession.getRut(), start, length, searchValue, tipoOrder, nombreDataColumnaActual));

        return SUCCESS;
    }
}
