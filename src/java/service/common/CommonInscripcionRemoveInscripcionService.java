/*
 * @(#)CommonInscripcionRemoveInscripcionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.GenericSession;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonInscripcionRemoveInscripcionService {

    /**
     * Method Servicio
     *
     * @param action
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(ActionCommonSupport action, GenericSession genericSession,
            Map<String, String[]> parameters, String key) {

        int retValue = genericSession.getWorkSession(key).getAluCar().removeInscripcionCoordinador(action,
                genericSession, parameters);

        if (retValue == 0) {
            return SUCCESS;
        } else {
            return "acta_emitida";
        }
    }
}
