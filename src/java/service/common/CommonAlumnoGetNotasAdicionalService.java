/*
 * @(#)CommonAlumnoGetNotasAdicionalService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import static infrastructure.util.common.CommonMallaUtil.getCalificacionRequisitoAdicionalLogroionales;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoGetNotasAdicionalService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param asignatura
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Integer asignatura, String key) {                         
        getCalificacionRequisitoAdicionalLogroionales(genericSession, asignatura, key);

        return SUCCESS;
    }
}
