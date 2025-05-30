/*
 * @(#)EgresadoLaboralesDeleteLaboralAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.egresado;

import static service.egresado.EgresadoLaboralesDeleteLaboralService.service;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Alvaro Romero C.
 */
public class EgresadoLaboralesDeleteLaboralAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(this, getGenericSession(), Manager.getEgresadoSession(sesion), getPos(), getKey());
    }

    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), Manager.getEgresadoSession(sesion).getFichaLaboralList());
    }
}
