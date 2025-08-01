/*
 * @(#)CommonAlumnoGetCurricularesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonAlumnoGetCurricularesService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonAlumnoGetCurriculares
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonAlumnoGetCurricularesAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){
        return new CommonAlumnoGetCurricularesService().service(getGenericSession(), getKey());
    }
}
