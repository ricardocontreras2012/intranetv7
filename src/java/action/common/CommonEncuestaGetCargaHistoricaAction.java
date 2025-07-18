/*
 * @(#)CommonEncuestaGetCargaHistoricaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonEncuestaGetCargaHistoricaService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL
 * CommonEncuestaGetCargaHistorica
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonEncuestaGetCargaHistoricaAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String tipo;
    

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {        
        return new CommonEncuestaGetCargaHistoricaService().service(getGenericSession(), getKey());
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
