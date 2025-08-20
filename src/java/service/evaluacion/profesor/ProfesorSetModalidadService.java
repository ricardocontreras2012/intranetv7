/*
 * @(#)ProfesorSetModalidadService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.evaluacion.profesor;

import session.GenericSession;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class ProfesorSetModalidadService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param modalidad
     * @param key
     *
     * @return
     */
    public String service(GenericSession genericSession, String modalidad, String key) {
        String retValue = null;

        if ("R".equals(modalidad)) {
            retValue = "relativa";
        } else {
            if ("A".equals(modalidad)) {
                retValue = "absoluta";
            }
        }

        LogUtil.setLogCurso(genericSession.getRut(), genericSession.getWorkSession(key).getCurso());

        return retValue;
    }
}
