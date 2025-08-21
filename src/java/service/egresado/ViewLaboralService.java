/*
 * @(#)ViewLaboralService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.egresado;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.EgresadoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Alvaro Romero C.
 */
public class ViewLaboralService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param es
     * @param pos
     * @param key
     *
     * @return
     */
    public String service(GenericSession genericSession, EgresadoSession es, Integer pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);        
        es.setFichaLaboral(ContextUtil.getDAO().getFichaLaboralRepository(ActionUtil.getDBUser()).find(ws.getAluCar().getAlumno().getAluRut(),
                es.getFichaLaboralList().get(pos).getFlabCorrelFicha()));

        return SUCCESS;
    }
}
