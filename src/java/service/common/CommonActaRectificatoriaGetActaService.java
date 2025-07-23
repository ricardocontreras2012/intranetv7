/*
 * @(#)CommonRectificatoriaGetActaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;


import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 08/05/2014
 */
public final class CommonActaRectificatoriaGetActaService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param pos
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     * @throws Exception Si el servicio genera una exception
     */
    public String service(GenericSession genericSession, Integer pos, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);

        Curso curso = ws.getCursoList().get(pos);
        ws.setActaRectificatoriaList(ContextUtil.getDAO().getCalificacionRepository(ActionUtil.getDBUser()).find(curso.getId()));
        ws.setCurso(curso);

        return SUCCESS;
    }
}
