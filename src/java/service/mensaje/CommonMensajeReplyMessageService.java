/*
 * @(#)CommonMensajeReplyMessageService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.mensaje;

import static com.opensymphony.xwork2.Action.SUCCESS;
import static java.lang.Math.min;
import static java.lang.String.valueOf;
import java.util.ArrayList;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MensajeNodeSupport;
import infrastructure.support.MensajeSupport;
import infrastructure.util.LogUtil;
import static infrastructure.util.SystemParametersUtil.MESSAGE_SUBJECT_MAX_LENGTH;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMensajeReplyMessageService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(GenericSession genericSession, Integer pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        MensajeSupport mensajeSupport = new MensajeSupport(genericSession.getEmail());
        MensajeNodeSupport rootNode = new MensajeNodeSupport();
        List<MensajeNodeSupport> lCommonMensajeNodeUtil = new ArrayList<>();
        MensajeNodeSupport messageNodeSupport = new MensajeNodeSupport();

        messageNodeSupport.setId(valueOf(ws.getReceivedMsgs().get(pos).getMensaje().getMsgRutEnv()));
        messageNodeSupport.setTerminal(true);
        lCommonMensajeNodeUtil.add(messageNodeSupport);

        String subject = "RE: " + ws.getReceivedMsgs().get(pos).getMensaje().getMsgSubject();

        subject = subject.substring(0, min(subject.length(), MESSAGE_SUBJECT_MAX_LENGTH));
        rootNode.setId("RE");
        rootNode.setValue("Replay");
        rootNode.setTerminal(true);
        rootNode.setState("SP");
        rootNode.setNodeList(lCommonMensajeNodeUtil);
        mensajeSupport.setRootNode(rootNode);
        mensajeSupport.setPara(ws.getReceivedMsgs().get(pos).getMensaje().getMsgNombreEnv());
        mensajeSupport.setSubject(subject);
        mensajeSupport.setCurrentNode(rootNode);
        ws.setMensajeSupport(mensajeSupport);

        LogUtil.setLog(genericSession.getRut(), ws.getCurrentMsg().getMsgCorrel());

        return SUCCESS;
    }
}
