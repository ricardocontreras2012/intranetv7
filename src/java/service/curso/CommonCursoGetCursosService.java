/*
 * @(#)CommonCursoGetCursosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.curso;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import static infrastructure.util.AppStaticsUtil.PRIVILEGED_USERS;
import infrastructure.util.ActionUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonCursoGetCursosService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param keyParent
     * @param actionCall
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key, String keyParent, String actionCall) {
        WorkSession ws = new WorkSession(ActionUtil.getDBUser());

        ws.setKeyParent(keyParent);
        genericSession.getSessionMap().put(key, ws);
        ws.setActionCall(actionCall);

        if (PRIVILEGED_USERS.containsKey(genericSession.getUserType()))
        {
            ws.setCursoList(genericSession.getWorkSession(keyParent).getCursoList());
        }

        return SUCCESS;
    }
}
