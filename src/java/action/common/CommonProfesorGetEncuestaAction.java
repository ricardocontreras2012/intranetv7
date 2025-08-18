/*
 * @(#)CommonProfesorGetEncuestaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.profesor.GetEncuestaService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonProfesorGetEncuesta
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonProfesorGetEncuestaAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer pos;
    private String tipo;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {    
        return new GetEncuestaService().service(this, getGenericSession(), pos, tipo, getKey());
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }   
}
