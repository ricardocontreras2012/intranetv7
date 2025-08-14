/*
 * @(#)EgresadoLaboralesNewLaboralAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.egresado;

import service.misdatos.egresado.EgresadoLaboralesNewLaboralService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Alvaro Romero C.
 */
public class EgresadoLaboralesNewLaboralAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new EgresadoLaboralesNewLaboralService().service(getGenericSession(), Manager.getEgresadoSession(sesion), getKey());
    }
}
