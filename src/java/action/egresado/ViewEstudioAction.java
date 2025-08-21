/*
 * @(#)ViewEstudioAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.egresado;

import service.egresado.ViewEstudioService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

//import support.action.ActionValidationSupport;
/**
 *
 * @author Alvaro Romero C.
 */
public class ViewEstudioAction extends ActionCommonSupport {

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
        return new ViewEstudioService().service(getGenericSession(), Manager.getEgresadoSession(sesion), correl, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCorrel() {
        return correl;
    }

    /**
     * Method description
     *
     * @param correl
     */
    public void setCorrel(Integer correl) {
        this.correl = correl;
    }
}
