/*
 * @(#)CommonEmpleadorGetEmpleadorxRutService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.Empleador;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonEmpleadorGetEmpleadorxRutService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param rut
     * @return Action status.
     */
    public Empleador service(GenericSession genericSession, String key, Integer rut) {        
       return ContextUtil.getDAO().getEmpleadorRepository(ActionUtil.getDBUser()).find(rut);
    }
}
