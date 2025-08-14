/*
 * @(#)RegistradorCurricularUserExternoGetNominaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.mantenedor.registradorcurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.RegistradorSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class RegistradorCurricularUserExternoGetNominaService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @return Action status.
     */
    public String service(GenericSession genericSession, RegistradorSession registradorSession) {
        registradorSession.setExternoList(ContextUtil.getDAO().getExternoRepository(ActionUtil.getDBUser()).find());

        return SUCCESS;
    }
}
