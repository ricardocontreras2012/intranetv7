/*
 * @(#)EgresadoLoginSeleccionarIngresoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.egresado;

import service.login.egresado.EgresadoLoginSeleccionarIngresoService;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL
 * EgresadoLoginSeleccionarIngreso
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class EgresadoLoginSeleccionarIngresoAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new EgresadoLoginSeleccionarIngresoService().service(getGenericSession(), getPos(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getAluCarList());
    }
}
