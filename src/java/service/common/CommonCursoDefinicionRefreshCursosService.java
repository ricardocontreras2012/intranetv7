/*
 * @(#)CommonCursoDefinicionRefreshCursosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.GenericSession;
import infrastructure.util.common.CommonCursoUtil;

/**
 * Class description
 *
 * @author Felipe
 * @version 04/04/2024
 */
public final class CommonCursoDefinicionRefreshCursosService {

    /**
     * Method Servicio
     *
     * @param genericSession
     * @param parameters
     * @param key
     * @return Action status
     */
    public String service(GenericSession genericSession,
            Map<String, String[]> parameters, String key) {

        CommonCursoUtil.getCursos(genericSession, "*", key); //Cerrados, Transversales y Espejos
        return SUCCESS;
    }

}
