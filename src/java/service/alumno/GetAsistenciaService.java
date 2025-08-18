/*
 * @(#)GetAsistenciaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class GetAsistenciaService {

    /**
     * Method Servicio
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        String retValue = SUCCESS;

        ws.setAsistenciaAlumnoNominaList(ContextUtil.getDAO().getAsistenciaAlumnoNominaRepository(ActionUtil.getDBUser()).find(
                ws.getCurso(), ws.getAluCar().getId().getAcaRut()));

        LogUtil.setLog(genericSession.getRut(), ws.getAluCar().getId().getAcaRut());

        if (ws.getAsistenciaAlumnoNominaList().isEmpty()) {
            retValue = "notDataFound";
            action.addActionMessage(action.getText("message.no.hay.asistencias"));
        }

        return retValue;
    }
}
