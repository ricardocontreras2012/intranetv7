/*
 * @(#)CommonMensajeSendFwdMessageService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Mensaje;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MensajeSenderSupport;
import infrastructure.support.MensajeSupport;
import infrastructure.thread.MailMensajeSenderThread;
import static infrastructure.util.ActionUtil.getURL;
import static infrastructure.util.DateUtil.getFormatedDate;
import static infrastructure.util.DateUtil.getSysdate;
import static infrastructure.util.SystemParametersUtil.DATE_FULL_FORMAT;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMensajeSendFwdMessageService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        MensajeSupport mensajeSupport = ws.getMensajeSupport();
        
        Mensaje mensajeFwd = ws.getMensajeFwd();
        Mensaje mensaje = new Mensaje();

        mensajeSupport.setMensaje(mensaje);
        mensaje.setMsgTipo("TXT");
        mensaje.setMsgRutEnv(genericSession.getRut());
        mensaje.setMsgNombreEnv(genericSession.getNombreMensaje());
        mensaje.setMsgRolEnv(genericSession.getUserType());
        mensaje.setMsgSubject("FWD: " + mensajeFwd.getMsgSubject());

        String msg = "Original enviado por: " + mensajeFwd.getMsgNombreEnv() + '\n';

        msg += "Fecha: " + getFormatedDate(mensajeFwd.getMsgFecha(), DATE_FULL_FORMAT)
                + "\n\n";
        msg += "----------Mensaje Original---------\n\n";
        msg += mensajeFwd.getMsgMsg();
        mensaje.setMsgMsg(msg);
        mensaje.setMsgPara(ws.getMensajeSupport().getPara());
        mensaje.setMsgFecha(getSysdate());
        mensaje.setMsgAttach(mensajeFwd.getMsgAttach());
        mensaje.setMensajeAttachList(mensajeFwd.getMensajeAttachList());
        mensaje.setMsgIdSession(key);

        MensajeSenderSupport sender = new MensajeSenderSupport(mensajeSupport);
        List<String> destList = sender.send();
        mensajeSupport.setDestList(destList);

        MailMensajeSenderThread thread = new MailMensajeSenderThread(mensajeSupport, getURL());
        thread.start();
        return SUCCESS;
    }
}
