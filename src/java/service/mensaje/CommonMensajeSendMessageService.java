/*
 * @(#)CommonMensajeSendMessageService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.mensaje;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Mensaje;
import domain.model.MensajeAttach;
import java.io.File;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MensajeSenderSupport;
import infrastructure.support.MensajeSupport;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.thread.MailMensajeSenderThread;
import static infrastructure.util.ActionUtil.getURL;
import infrastructure.util.LogUtil;
import static infrastructure.util.common.CommonArchivoUtil.doUpload;
import static infrastructure.util.common.CommonArchivoUtil.getAttachFileName;
import infrastructure.util.common.CommonSequenceUtil;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMensajeSendMessageService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param tipo
     * @param subject
     * @param messageText
     * @param page
     * @param url
     * @param upload
     * @param uploadFileName
     * @param imagen
     * @param imagenFileName
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(ActionCommonSupport action, GenericSession genericSession,
            String tipo, String subject, String messageText, String page, String url,
            File[] upload, String[] uploadFileName,
            File imagen, String imagenFileName,
            String key) {

        try {

            Mensaje mensaje = new Mensaje();
            WorkSession ws = genericSession.getWorkSession(key);

            MensajeSupport mensajeSupport = ws.getMensajeSupport();

            getAttachFiles(action, upload, uploadFileName, mensaje);

            mensaje.setMsgRutEnv(genericSession.getRut());
            mensaje.setMsgNombreEnv(genericSession.getNombreMensaje());
            mensaje.setMsgRolEnv(genericSession.getUserType());
            mensaje.setMsgSubject(subject);
            mensaje.setMsgMsg(messageText);
            mensaje.setMsgPara(mensajeSupport.getPara());
            mensaje.setMsgIdSession(key);
            mensaje.setMsgTipo(tipo);

            switch (tipo) {
                case "TXT":
                    mensaje.setMsgUrl("");
                    mensaje.setMsgImagen("");
                    break;
                case "HTML":
                    mensaje.setMsgUrl(url);
                    mensajeSupport.setPage(page);
                    break;
                case "IMG":
                    getImageFile(action, imagen, imagenFileName, mensaje);
                    break;
            }

            mensajeSupport.setMensaje(mensaje);
            mensajeSupport.setFacultad(genericSession.getFacultad());

            MensajeSenderSupport sender = new MensajeSenderSupport(mensajeSupport);
            List<String> destList = sender.send();
            mensajeSupport.setDestList(destList);

            MailMensajeSenderThread thread = new MailMensajeSenderThread(mensajeSupport, getURL());
            thread.start();

            LogUtil.setLog(genericSession.getRut(), mensaje.getMsgCorrel());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    /**
     * Method description
     *
     *
     * @param action
     * @param upload
     * @param uploadFileName
     * @param mensaje
     */
    private void getAttachFiles(ActionCommonSupport action, File[] upload, String[] uploadFileName, Mensaje mensaje) throws Exception {
        mensaje.setMsgAttach("N");

        if (upload == null || upload.length == 0) {
            return; // Si no hay archivos, no se hace nada
        }

        mensaje.setMsgAttach("S");
        Integer folio = CommonSequenceUtil.getDocumentSeq();

        // Usamos un IntStream para mapear los archivos a MensajeAttach
        List<MensajeAttach> attachList
                = IntStream.range(0, upload.length) // Itera sobre los índices
                        .mapToObj(i -> {
                            MensajeAttach mensajeAttach = new MensajeAttach();
                            mensajeAttach.setMenaAttachFile(uploadFileName[i]);
                            return mensajeAttach;
                        })
                        .collect(Collectors.toList());

        // Usar los streams para renombrar y subir archivos
        IntStream.range(0, attachList.size()) // Itera sobre los índices
                .forEach(i -> {
                    MensajeAttach mensajeAttach = attachList.get(i);
                    String nombre = getAttachFileName(mensajeAttach.getMenaAttachFile(), "_" + i, folio);
                    mensajeAttach.setMenaAttachFile(nombre);
                    try {
                        doUpload(action, upload[i], nombre, "msg");
                    } catch (Exception e) {
                        e.printStackTrace(); // Manejo de la excepción
                    }
                });

        mensaje.setMensajeAttachList(attachList); // Establecer la lista de archivos adjuntos en el mensaje
    }

    private void getImageFile(ActionCommonSupport action, File imagen, String imagenFileName, Mensaje mensaje) {

        if (imagen != null) {
            Integer folio = CommonSequenceUtil.getDocumentSeq();
            String nombre = getAttachFileName(imagenFileName, "_" + 0, folio);

            try {
                mensaje.setMsgImagen(nombre);
                doUpload(action, imagen, nombre, "msg");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
