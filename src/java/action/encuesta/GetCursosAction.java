/*
 * @(#)GetCursosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.encuesta;

import service.encuesta.GetCursosService;
import infrastructure.support.action.common.ActionCommonAgnoSemSupport;

/**
 * Procesa el action mapeado del request a la URL CommonEncuestaGetCursos
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetCursosAction extends ActionCommonAgnoSemSupport {

    private static final long serialVersionUID = 1L;
    private Integer pos;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetCursosService().service(getGenericSession(), getAgno(), getSem(), pos, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getPos() {
        return pos;
    }

    /**
     * Method description
     *
     * @param pos
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }
}
