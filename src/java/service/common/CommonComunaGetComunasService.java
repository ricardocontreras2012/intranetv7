/*
 * @(#)CommonComunaGetComunasService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.Comuna;
import session.GenericSession;
import infrastructure.util.ContextUtil;
import java.util.List;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonComunaGetComunasService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param region
     * @return Action status.
     */
    public List<Comuna> service(GenericSession genericSession, String key, Integer region) {        
        return ContextUtil.getComunaMap().get(region);        
    }
}
