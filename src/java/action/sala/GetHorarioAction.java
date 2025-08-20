/*
 * @(#)GetHorarioAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.sala;

import service.sala.GetHorarioService;
import infrastructure.support.action.ActionValidationAgnoSemSupport;

/**
 * Procesa el action mapeado del request a la URL CommonSalaGetHorario
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetHorarioAction extends ActionValidationAgnoSemSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetHorarioService().service(getGenericSession(), getPos(), getAgno(), getSem(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getSalaList());
    }

}
