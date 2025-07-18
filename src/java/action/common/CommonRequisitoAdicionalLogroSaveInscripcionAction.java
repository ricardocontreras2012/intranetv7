/*
 * @(#)CommonRequisitoAdicionalLogroSaveInscripcionAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonRequisitoAdicionalLogroSaveInscripcionService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL
 * CommonRequisitoAdicionalLogroSaveInscripcion
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonRequisitoAdicionalLogroSaveInscripcionAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String fecha;
    private Integer trequisitoLogroAdicional;
    private String tema;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonRequisitoAdicionalLogroSaveInscripcionService().service(getGenericSession(), tema, fecha, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public String getTema() {
        return tema;
    }

    /**
     * Method description
     *
     * @param tema
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Method description
     *
     * @param fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     *
     * @return
     */
    public Integer getTrequisitoLogroAdicional() {
        return trequisitoLogroAdicional;
    }

    /**
     *
     * @param trequisitoLogroAdicional
     */
    public void setTrequisitoLogroAdicional(Integer trequisitoLogroAdicional) {
        this.trequisitoLogroAdicional = trequisitoLogroAdicional;
    }
}
