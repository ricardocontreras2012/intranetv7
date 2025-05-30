/*
 * @(#)CommonCarreraGetCarrerasService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonCarreraGetCarrerasService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        CommonUtil.setAgnoSemAct(ws);
        ws.setMencionList(ContextUtil.getDAO().getMencionPersistence(ActionUtil.getDBUser()).find(genericSession.getUserType(), genericSession.getRut()));

        return SUCCESS;
    }
}
