/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.NONE;
import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import domain.repository.ComentarioEncuestaAyudantePersistence;
import domain.repository.RespEnctaAyuCursoPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo
 */
public class CommonAyudanteGetEncuestaService {
    public static String service(ActionCommonSupport action, GenericSession genericSession, String key) {
         WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = ws.getCurso();

        RespEnctaAyuCursoPersistence respEnctaAyuCursoPersistence
                = ContextUtil.getDAO().getRespEnctaAyuCursoPersistence(ActionUtil.getDBUser());
        ComentarioEncuestaAyudantePersistence comenEnctaPersistence
                = ContextUtil.getDAO().getComentarioEncuestaAyudantePersistence(ActionUtil.getDBUser());

        ws.setRespEnctaAyu(respEnctaAyuCursoPersistence.find(curso.getId()));
        ws.setComentarioEncuestaAyudanteList(comenEnctaPersistence.find(curso));
        String retValue = SUCCESS;

        if (ws.getRespEnctaAyu() == null || ws.getRespEnctaAyu().isEmpty()) {
            retValue = NONE;
            action.addActionMessage(action.getText("message.encuesta.sin.responder"));
        }

        LogUtil.setLogCurso(genericSession.getRut(), curso);

        return retValue;

    }
}