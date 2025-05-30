/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.profesor;

import com.opensymphony.xwork2.Action;
import domain.model.Curso;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo
 */
public class ProfesorActaRectificatoriaGetNominaService {

    public static String service(GenericSession genericSession, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);

        Curso curso = ws.getCurso();

        ws.setActaRectificatoriaList(ContextUtil.getDAO().getCalificacionPersistence(ActionUtil.getDBUser()).find(curso.getId()));
        
        LogUtil.setLogCurso(genericSession.getRut(), curso);
        return Action.SUCCESS;
    }
}
