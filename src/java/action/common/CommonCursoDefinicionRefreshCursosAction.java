/*
 * @(#)CommonCursoDefinicionRefreshCursosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.curso.CommonCursoDefinicionRefreshCursosService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL CommonCursoDefinicionRefreshCursos
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonCursoDefinicionRefreshCursosAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     *
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonCursoDefinicionRefreshCursosService().service(getGenericSession(), getMapParameters(), getKey());
    }
}
