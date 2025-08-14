/*
 * @(#)CommonCursoGetFotoAlumnoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import java.io.InputStream;
import service.curso.CommonCursoGetFotoAlumnoService;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL CommonCursoGetFotoAlumno
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonCursoGetFotoAlumnoAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;
    private InputStream image;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        image = new CommonCursoGetFotoAlumnoService().service(getGenericSession(), getPos(), getKey());

        return SUCCESS;
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getNominaCurso());
    }

    /**
     * Method description
     *
     * @return
     */
    public InputStream getImage() {
        return image;
    }
}
