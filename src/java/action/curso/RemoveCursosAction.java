/*
 * @(#)RemoveCursosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.curso;

import service.curso.RemoveCursosService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL CommonCursoDefinicionRemoveCursos
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class RemoveCursosAction extends ActionParameterAwareSupport {

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
        return new RemoveCursosService().service(getGenericSession(), getMapParameters(), getKey());
    }
}
