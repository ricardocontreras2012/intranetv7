/*
 * @(#)DeleteLaboralAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.egresado;

import service.egresado.DeleteLaboralService;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Alvaro Romero C.
 */
public class DeleteLaboralAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new DeleteLaboralService().service(this, getGenericSession(), Manager.getEgresadoSession(sesion), getPos(), getKey());
    }

    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), Manager.getEgresadoSession(sesion).getFichaLaboralList());
    }
}
