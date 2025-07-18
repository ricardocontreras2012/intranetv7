/*
 * @(#)CommonAlumnoGetEvaluacionesParcialesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.common.CommonCursoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoGetEvaluacionesParcialesService {

    /**
     * Method Servicio
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(ActionCommonSupport action, GenericSession genericSession,
            String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        ws.getAluCar().setEvaluacionAlumnoList(CommonCursoUtil.getParent(ws.getCurso(), ws.getCargaEspejo()));

        String retValue = SUCCESS;

        if (ws.getAluCar().getEvaluacionAlumnoList().isEmpty()) {
            retValue = "notDataFound";
            action.addActionMessage(action.getText("message.no.hay.evaluaciones"));
        }

        return retValue;
    }
}
