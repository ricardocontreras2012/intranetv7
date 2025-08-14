/*
 * @(#)CommonMensajeSendMessageAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import java.io.File;
import service.mensaje.CommonMensajeSendMessageService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonMensajeSendMessage
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMensajeSendMessageAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String messageText;
    private String subject;
    private File[] upload;
    private String[] uploadFileName;
    private String imagenFileName;
    private File imagen;
    private String tipo;
    private String messageHtml;
    private String url;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonMensajeSendMessageService().service(this, getGenericSession(), tipo, subject, messageText, messageHtml, url, upload,
                uploadFileName, imagen, imagenFileName, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Method description
     *
     * @param messageText
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Method description
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Method description
     *
     *
     * @return a defensive copy of the field.
     */
    public File[] getUpload() {
        return upload.clone();
    }

    /**
     * Method description
     *
     *
     * @param upload
     */
    public void setUpload(File[] upload) {
        this.upload = upload.clone();
    }

    /**
     * Method description
     *
     *
     * @return a defensive copy of the field.
     */
    public String[] getUploadFileName() {
        return uploadFileName.clone();
    }

    /**
     * Method description
     *
     *
     * @param uploadFileName
     */
    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileName = uploadFileName.clone();
    }

    public String getMessageHtml() {
        return messageHtml;
    }

    public void setMessageHtml(String messageHtml) {
        this.messageHtml = messageHtml;
    }

    public String getImagenFileName() {
        return imagenFileName;
    }

    public void setImagenFileName(String imagenFileName) {
        this.imagenFileName = imagenFileName;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
