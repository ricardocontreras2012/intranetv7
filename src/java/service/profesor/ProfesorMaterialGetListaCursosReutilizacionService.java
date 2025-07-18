/*
 * @(#)ProfesorMaterialGetListaCursosReutilizacionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import domain.model.CursoId;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonCursoUtil;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorMaterialGetListaCursosReutilizacionService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param keyParent
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key, String keyParent) {
        WorkSession ws = new WorkSession(ActionUtil.getDBUser());
        ws.setKeyParent(keyParent);
        genericSession.getSessionMap().put(key, ws);
        ws.setCursoList(getLista(genericSession, keyParent));
        LogUtil.setLogCurso(genericSession.getRut(), genericSession.getWorkSession(keyParent).getCurso());

        return SUCCESS;
    }

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    private Integer getMinAgno(GenericSession genericSession, String key) {
        return genericSession.getWorkSession(key).getCarga().stream()
                .mapToInt(curso -> curso.getId().getCurAgno())
                .min()
                .orElse(3000);
    }

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    private List<Curso> getLista(GenericSession genericSession, String key) {   
        CursoId cursoActualId = genericSession.getWorkSession(key).getCurso().getId();
        Integer agnoInicio = getMinAgno(genericSession, key) - 4;
        List<Curso> cursoList = ContextUtil.getDAO().getProfesorPersistence(ActionUtil.getDBUser()).findCursosMaterialHistorico(
                genericSession.getRut(), agnoInicio);

        return cursoList != null ? cursoList.stream()
                .filter(curso -> !CommonCursoUtil.equals(curso.getId(), cursoActualId))
                .collect(Collectors.toList()) : Collections.emptyList();
    }
}
