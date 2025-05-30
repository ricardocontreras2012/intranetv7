/*
 * @(#)AlumnoInscripcionGetDerechosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoInscripcionGetDerechosService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public static String service(GenericSession genericSession, String key) {
        genericSession.getWorkSession(key).getAluCar().setDerechosAlumno();

        return SUCCESS;
    }
}
