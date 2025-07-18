/*
 * @(#)ProfesorEvaluacionEnableFormService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

import domain.model.Curso;
import session.GenericSession;
import session.WorkSession;

/**
 *
 * @author Ricardo Contreras S.
 */
public class ProfesorEvaluacionEnableFormService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param key
     *
     * @return
     */
    public String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = ws.getCurso();

        String modalidad = curso.getCurModalEval();
        String retValue = "none";

        if ("R".equals(modalidad)) {
            retValue = "relativa";
        } else {
            if ("A".equals(modalidad)) {
                retValue = "absoluta";
            }
        }

        return retValue;
    }
}
