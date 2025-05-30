/*
 * @(#)EgresadoLaboralesNewLaboralService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */


package service.egresado;


import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.repository.AlumnoEmpleadorPersistence;
import session.EgresadoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;


/**
 *
 * @author Alvaro Romero C.
 */
public class EgresadoLaboralesNewLaboralService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param es
     * @param key
     *
     * @return
     */
    public static String service(GenericSession genericSession, EgresadoSession es, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        es.setAreaTrabajoList(ContextUtil.getAreaTrabajoList());
        AlumnoEmpleadorPersistence alumnoEmpleadorPersistence
                = ContextUtil.getDAO().getAlumnoEmpleadorPersistence(ActionUtil.getDBUser());

        es.setAlumnoEmpleadorList(
                alumnoEmpleadorPersistence.find(ws.getAluCar().getAlumno().getAluRut()));
        return SUCCESS;
    }
}
