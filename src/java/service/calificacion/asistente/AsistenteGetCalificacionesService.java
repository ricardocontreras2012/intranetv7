/*
 * @(#)AsistenteGetCalificacionesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.calificacion.asistente;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Felipe
 * @version 7, 17/10/2023
 */
public final class AsistenteGetCalificacionesService {
    
        /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        
        WorkSession ws = genericSession.getWorkSession(key);
        ws.getAluCar().setCartolaAgnoView(Integer.parseInt(parameters.get("agno")[0]));
        LogUtil.setLog(genericSession.getRut(), ws.getAluCar().getId().getAcaRut());

        return SUCCESS;
    }    
}
