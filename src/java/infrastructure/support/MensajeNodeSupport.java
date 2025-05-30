/*
 * @(#)MensajeNodeSupport.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.support;

import java.io.Serializable;
import java.util.List;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class MensajeNodeSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private List<MensajeNodeSupport> nodeList;
    private List<MensajeNodeSupport> nodeListBar;
    private String state;    // SP: sin Procesar, PR: Procesado
    private boolean terminal;
    private String value;

    /**
     * Method description
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Method description
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<MensajeNodeSupport> getNodeList() {
        return nodeList;
    }

    /**
     * Method description
     *
     * @param nodeList
     */
    public void setNodeList(List<MensajeNodeSupport> nodeList) {
        this.nodeList = nodeList;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<MensajeNodeSupport> getNodeListBar() {
        return nodeListBar;
    }

    /**
     * Method description
     *
     * @param nodeListBar
     */
    public void setNodeListBar(List<MensajeNodeSupport> nodeListBar) {
        this.nodeListBar = nodeListBar;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     * Method description
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Method description
     *
     * @return
     */
    public boolean isTerminal() {
        return terminal;
    }

    /**
     * Method description
     *
     * @param terminal
     */
    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Method description
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
