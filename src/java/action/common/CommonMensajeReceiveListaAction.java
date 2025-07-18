/*
 * @(#)CommonMensajeReceiveListaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonMensajeReceiveListaService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL CommonMensajeReceiveLista
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMensajeReceiveListaAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;
    private Integer largoBarra;
    private Integer largoLista;
    private String nombreLista;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonMensajeReceiveListaService().service(getGenericSession(), getMapParameters(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getLargoBarra() {
        return largoBarra;
    }

    /**
     * Method description
     *
     * @param largoBarra
     */
    public void setLargoBarra(Integer largoBarra) {
        this.largoBarra = largoBarra;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getLargoLista() {
        return largoLista;
    }

    /**
     * Method description
     *
     * @param largoLista
     */
    public void setLargoLista(Integer largoLista) {
        this.largoLista = largoLista;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getNombreLista() {
        return nombreLista;
    }

    /**
     * Method description
     *
     * @param nombreLista
     */
    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }
}
