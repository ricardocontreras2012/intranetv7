/*
 * @(#)AlumnoInscripcionCambioMencionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.AlumnoSession;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoInscripcionCambioMencionService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param alumnoSession
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public static String service(GenericSession genericSession, AlumnoSession alumnoSession,  String key) { 
        AluCar aluCar = genericSession.getWorkSession(key).getAluCar();
   
        ContextUtil.getDAO().getInscripcionPersistence(ActionUtil.getDBUser()).cambioMencion(
                aluCar, alumnoSession.getNewMencion(), aluCar.getParametros().getAgnoIns(), aluCar.getParametros().getSemIns());
        
        LogUtil.setLog(genericSession.getRut());
        return SUCCESS;
    }
}
