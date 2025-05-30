/*
 * @(#)CommonEmailEnableEmailService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonEmailEnableEmailService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public static String service(GenericSession genericSession, String key) {
        genericSession.getWorkSession(key).setActionCall(ContextUtil.getDAO().getLogActionPersistence(genericSession.getUserType()).setEmail(genericSession.getUserType()));

        return SUCCESS;
    }
}
