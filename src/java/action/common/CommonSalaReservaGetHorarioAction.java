/*
 * @(#)CommonSalaReservaGetHorarioAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonSalaReservaGetHorarioService;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL CommonSalaReservaGetHorario
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonSalaReservaGetHorarioAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;
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
        return new CommonSalaReservaGetHorarioService().service(getGenericSession(), getPos(), inicio, termino, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException { 
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getSalaList());
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
