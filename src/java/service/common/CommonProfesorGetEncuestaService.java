/*
 * @(#)CommonProfesorGetEncuestaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.NONE;
import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonProfesorGetEncuestaService {

    /**
     * Method Servicio
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param pos
     * @param tipo
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(ActionCommonSupport action, GenericSession genericSession, Integer pos, String tipo, String key) {
             
        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = ws.getCursoProfesorList().get(pos).getCurso();

        ws.setCurso(curso);
        if (!"PR".equals(genericSession.getUserType()))
        {
            ws.setProfesor(ws.getCursoProfesorList().get(pos).getProfesor());
        } 

        ws.setRespEncta(ContextUtil.getDAO().getRespEnctaCursoPersistence(ActionUtil.getDBUser()).find(curso.getId(), ws.getProfesor().getProfRut(), tipo));

        ws.setComentarioEncuestaDocenteList(ContextUtil.getDAO().getComentarioEncuestaDocentePersistence(ActionUtil.getDBUser()).find(curso,ws.getProfesor().getProfRut(), tipo));
        
        String retValue = SUCCESS;

        if (ws.getRespEncta() == null || ws.getRespEncta().isEmpty()) {
            retValue = NONE;
            action.addActionMessage(action.getText("message.encuesta.sin.responder"));
        }

        LogUtil.setLogCurso(genericSession.getRut(), curso);

        return retValue;        
    }
}
