/*
 * @(#)AlumnoCertificacionGetCertificadosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.AlumnoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoCertificacionGetCertificadosService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param alumnoSession
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(GenericSession genericSession, AlumnoSession alumnoSession,
            String key) {        
        WorkSession ws = genericSession.getWorkSession(key);
        ws.setTramites(ContextUtil.getTramiteList());
        alumnoSession.setCertList(ContextUtil.getDAO().getCertificacionViewRepository(ActionUtil.getDBUser()).find(ws.getAluCar().getId()));
        
        return SUCCESS;     
    }
}
