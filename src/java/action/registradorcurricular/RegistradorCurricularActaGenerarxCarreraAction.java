/*
 * @(#)RegistradorCurricularActaGenerarxCarreraAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.registradorcurricular;

import static service.registradorcurricular.RegistradorCurricularActaGenerarxCarreraService.service;
import session.Manager;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL
 * RegistradorCurricularActaGenerarxCarrera
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class RegistradorCurricularActaGenerarxCarreraAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;
    private Integer agno;
    private Integer sem;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), Manager.getRegistradorSession(sesion), getMapParameters(), agno, sem,
                getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAgno() {
        return agno;
    }

    /**
     * Method description
     *
     * @param agno
     */
    public void setAgno(Integer agno) {
        this.agno = agno;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getSem() {
        return sem;
    }

    /**
     * Method description
     *
     * @param sem
     */
    public void setSem(Integer sem) {
        this.sem = sem;
    }
}
