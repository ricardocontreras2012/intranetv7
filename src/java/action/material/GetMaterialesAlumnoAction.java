/*
 * @(#)GetMaterialesAlumnoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */


package action.material;


import service.material.GetMaterialesAlumnoService;
import infrastructure.support.action.post.ActionPostValidationSupport;


/**
 *
 * @author Ricardo Contreras S.
 */
public class GetMaterialesAlumnoAction extends ActionPostValidationSupport {
    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetMaterialesAlumnoService().service(this, getGenericSession(), getKey(), getPos());
    }

    /**
     * Method description
     *
     *
     * @return
     *
     * @throws IllegalArgumentException
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getNominaCurso());
    }
}
