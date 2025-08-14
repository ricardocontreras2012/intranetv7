/*
 * @(#)CommonProfesorGetCargaHistoricaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonProfesorGetCargaHistoricaService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param keyParent
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key, String keyParent) {
        WorkSession ws = new WorkSession(ActionUtil.getDBUser());

        genericSession.getSessionMap().put(key, ws);

        WorkSession wsParent = genericSession.getWorkSession(keyParent);

        wsParent.getProfesor().setCargaHistorica();
        ws.setCursoList(wsParent.getProfesor().getCargaHistorica());               
        ws.setProfesor(wsParent.getProfesor());
        
        LogUtil.setLog(genericSession.getRut(), ws.getProfesor().getProfRut());

        return SUCCESS;
    }
}
