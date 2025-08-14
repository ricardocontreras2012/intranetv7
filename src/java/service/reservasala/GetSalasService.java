/*
 * @(#)GetSalasService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.reservasala;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class GetSalasService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     * @throws java.lang.Exception
     */
    public String service(GenericSession genericSession, String key) throws Exception{
        WorkSession ws = genericSession.getWorkSession(key);
        ws.setSalaList(ContextUtil.getDAO().getSalaRepository(ActionUtil.getDBUser()).findPropias(genericSession.getRut()));

        return SUCCESS;
    }
}
