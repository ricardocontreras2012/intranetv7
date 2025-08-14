/*
 * @(#)CommonCursoGetCursoActualxAgnoSemService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.curso;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonCursoGetCursoActualxAgnoSemService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(GenericSession genericSession, Integer pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        
        ws.setCurso(ws.getCursoList().get(pos));

        return SUCCESS;
    }
}
