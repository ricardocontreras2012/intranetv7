/*
 * @(#)CommonCursoDefinicionRemoveCursosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.CursoId;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonCursoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonCursoDefinicionRemoveCursosService {

    /**
     * Method Servicio
     *
     * @param genericSession
     * @param parameters
     * @param key
     * @return Action status
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        // Filtrar y eliminar los cursos seleccionados en una sola pasada
        ws.getCursoList().stream()
                .filter(curso -> parameters.containsKey("ck_" + ws.getCursoList().indexOf(curso))) // Verificar si el parámetro existe
                .forEach(curso -> {
                    CursoId id = curso.getId();
                    LogUtil.setLog(genericSession.getRut(), curso.getCodigo(" "));
                    ContextUtil.getDAO().getCursoRepository(ActionUtil.getDBUser()).remove(id);
                });

        // Actualizar la lista de cursos (Cerrados, Transversales y Espejos)
        CommonCursoUtil.getCursos(genericSession, "*", key);

        return SUCCESS;
    }

}
