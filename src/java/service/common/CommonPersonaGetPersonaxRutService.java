/*
 * @(#)CommonPersonaGetPersonaxRutService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.Persona;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonPersonaGetPersonaxRutService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param rut
     * @return Action status.
     */
    public Persona service(GenericSession genericSession, String key, Integer rut) { 
        return ContextUtil.getDAO().getPersonaPersistence(ActionUtil.getDBUser()).find(rut);        
    }
}
