/*
 * @(#)CommonSolicitudGetSolicitudesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;


import static service.common.CommonSolicitudGetSolicitudesService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonSolicitudGetSolicitudes
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonSolicitudGetSolicitudesAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer estado;
    private String inicio;
    private String termino;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        String retValue = service(getGenericSession(), getKey(), estado, inicio, termino);
        inicio = getGenericSession().getWorkSession(getKey()).getFechaInicio();
        termino = getGenericSession().getWorkSession(getKey()).getFechaTermino();
        return retValue;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    
    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }
    
}
