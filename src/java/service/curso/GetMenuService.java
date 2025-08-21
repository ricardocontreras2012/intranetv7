/*
 * @(#)CommonCursoGetCursoActualService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.curso;

import domain.model.Curso;
//import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.CollectionUtils;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.common.CommonCursoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class GetMenuService {

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
        Curso curso = ws.getCursoList().get(pos);

        if (!CollectionUtils.isEmpty(CommonCursoUtil.getProfesores(curso))) {
            ws.setProfesor(CommonCursoUtil.getProfesores(curso).get(0));
        }
        ws.setCurso(curso);
        return genericSession.getUserType();
    }
}
