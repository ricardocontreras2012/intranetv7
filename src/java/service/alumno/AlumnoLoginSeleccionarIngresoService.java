/*
 * @(#)AlumnoLoginSeleccionarIngresoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import session.AlumnoSession;
import session.GenericSession;
import static infrastructure.util.common.CommonAlumnoUtil.login;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoLoginSeleccionarIngresoService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param alumnoSession
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public static String service(GenericSession genericSession, AlumnoSession alumnoSession, Integer pos, String key) {
        login(genericSession, alumnoSession, genericSession.getWorkSession(key).getAluCarList().get(pos), key);        

        return "stack";
    }
}
