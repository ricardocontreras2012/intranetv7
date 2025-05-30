/*
 * @(#)CommonProfesorGetProfesorService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Profesor;
import session.GenericSession;
import session.WorkSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonProfesorGetProfesorService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, Integer pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        if (!ws.getProfesorList().isEmpty()) {
            Profesor profesor = ws.getProfesorList().get(pos);

            ws.setProfesor(profesor);
            profesor.setCarga();
            ws.setNombre(profesor.getNombre());
        }

        return SUCCESS;
    }
}
