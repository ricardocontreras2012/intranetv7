/*
 * @(#)CommonAlumnoGetCarreraAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.alumno.GetCarreraService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonAlumnoGetCarrera
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonAlumnoGetCarreraAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String actionNested;
    private Integer pos;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetCarreraService().service(this, getGenericSession(), pos, getKey());
    }

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

    /**
     * Method description
     *
     *
     * @return
     */
    public String getActionNested() {
        return actionNested;
    }

    /**
     * Method description
     *
     *
     * @param actionNested
     */
    public void setActionNested(String actionNested) {
        this.actionNested = actionNested;
    }
}
