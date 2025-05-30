/*
 * @(#)MensajeSupport.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.support;

import domain.model.Mensaje;
import java.io.Serializable;
import static java.util.Arrays.copyOf;
import java.util.List;

/**
 *
 * @author Ricardo Contreras S.
 */
public class MensajeSupport implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private String[] attach = new String[3];
    private String para = "";
    private String subject = "";
    private MensajeNodeSupport currentNode;
    private String emailSender;
    private Mensaje mensaje;
    private MensajeNodeSupport rootNode;
    private Integer facultad;
    private String page;
    private List<String> destList;

    /**
     *
     * @param emailSender
     */
    public MensajeSupport(String emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Mensaje getMensaje() {
        return mensaje;
    }

    /**
     * Method description
     *
     *
     * @param mensaje
     */
    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Method description
     *
     * @return a defensive copy of the field. The caller may change the state of
     * the returned object in any way, without affecting the internals of this
     * class.
     */
    public String[] getAttach() {
        return copyOf(this.attach, this.attach.length);
    }

    /**
     * Method description
     *
     * @param attach
     */
    public void setAttach(String[] attach) {
        this.attach = copyOf(attach, attach.length);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getPara() {
        return para;
    }

    /**
     * Method description
     *
     *
     * @param para
     */
    public void setPara(String para) {
        this.para = para;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Method description
     *
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
     * @return
     */
    public MensajeNodeSupport getCurrentNode() {
        return currentNode;
    }

    /**
     * Method description
     *
     *
     * @param currentNode
     */
    public void setCurrentNode(MensajeNodeSupport currentNode) {
        this.currentNode = currentNode;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public MensajeNodeSupport getRootNode() {
        return rootNode;
    }

    /**
     * Method description
     *
     *
     * @param rootNode
     */
    public void setRootNode(MensajeNodeSupport rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getEmailSender() {
        return emailSender;
    }

    /**
     *
     * @return
     */
    public Integer getFacultad() {
        return facultad;
    }

    /**
     *
     * @param facultad
     */
    public void setFacultad(Integer facultad) {
        this.facultad = facultad;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<String> getDestList() {
        return destList;
    }

    public void setDestList(List<String> destList) {
        this.destList = destList;
    }

    /**
     * Method description
     *
     *
     * @return
     *
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
