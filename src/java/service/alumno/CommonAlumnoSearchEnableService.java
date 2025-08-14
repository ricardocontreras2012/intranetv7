/*
 * @(#)CommonAlumnoSearchEnableService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoSearchEnableService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param actionCall
     * @param actionNested
     * @param typeSearch
     * @param key LLave para acceder a los datos de la sesion.
     * @param keyParent
     * @return Action status.
     */
    public String service(GenericSession genericSession, String actionCall, String actionNested,
            String typeSearch, String key, String keyParent) {
        WorkSession ws = new WorkSession(ActionUtil.getDBUser());

        ws.setKeyParent(keyParent);

        genericSession.getSessionMap().put(key, ws);
        ws.setActionCall(actionCall);
        ws.setActionNested(actionNested);
        ws.setTypeSearch(typeSearch);

        return SUCCESS;
    }
}
