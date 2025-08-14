/*
 * @(#)EgresadoEstudiosDeleteEstudioAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.egresado;

import service.misdatos.egresado.EgresadoEstudiosDeleteEstudioService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Alvaro Romero C.
 */
public class EgresadoEstudiosDeleteEstudioAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer correl;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new EgresadoEstudiosDeleteEstudioService().service(this, getGenericSession(), correl);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getCorrel() {
        return correl;
    }

    /**
     * Method description
     *
     *
     * @param correl
     */
    public void setCorrel(Integer correl) {
        this.correl = correl;
    }
}
