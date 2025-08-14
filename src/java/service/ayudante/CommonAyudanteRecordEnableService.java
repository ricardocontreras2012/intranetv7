/*
 * @(#)CommonAyudanteRecordEnableService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.ayudante;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAyudanteRecordEnableService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        ws.getAyudante().setCarga();
        ws.setCursoList(ws.getAyudante().getCarga());

        return SUCCESS;
    }
}
