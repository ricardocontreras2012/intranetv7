/*
 * @(#)SearchService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Profesor;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import static infrastructure.util.common.CommonProfesorUtil.getProfesor;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class SearchService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param rut
     * @param paterno
     * @param materno
     * @param nombre
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Integer rut, String paterno, String materno,
            String nombre, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        List<Profesor> nomina = getProfesor(rut,paterno,materno,nombre);

        ws.setProfesorList(nomina);

        return SUCCESS;
    }
}
