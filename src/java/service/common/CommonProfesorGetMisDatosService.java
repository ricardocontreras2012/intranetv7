/*
 * @(#)CommonProfesorGetMisDatosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Profesor;
import domain.repository.ProfesorPersistence;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonProfesorGetMisDatosService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, String key) {      
        ProfesorPersistence profesorPersistence
                = ContextUtil.getDAO().getProfesorPersistence(ActionUtil.getDBUser());
        Profesor profesorAux = profesorPersistence.getMisDatos(genericSession.getRut());
        Profesor profesor = genericSession.getWorkSession(key).getProfesor();


        profesor.setComuna(profesorAux.getComuna());
        profesor.setProfDireccion(profesorAux.getProfDireccion());
        profesor.setProfFono(profesorAux.getProfFono());
        profesor.setProfFechaNac(profesorAux.getProfFechaNac());
        profesor.setProfEmail(profesorAux.getProfEmail());
        return SUCCESS;
    }
}
