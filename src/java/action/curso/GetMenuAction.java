/*
 * @(#)GetMenuAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.curso;

import service.curso.GetMenuService;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL CommonCursoGetCursoActual
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetMenuAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetMenuService().service(getGenericSession(), getPos(), getKey());
    }
    
    @Override
    public boolean isValidParam() throws IllegalArgumentException {        
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getCursoList());
    }   
}
