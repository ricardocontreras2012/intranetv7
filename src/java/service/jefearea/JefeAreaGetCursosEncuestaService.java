/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.jefearea;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonUtil;

/**
 *
 * @author Administrador
 */
public class JefeAreaGetCursosEncuestaService {

    public static String service(GenericSession genericSession, String key, Integer agno, Integer sem) { 
        WorkSession ws = genericSession.getWorkSession(key);      
        
        CommonUtil.setAgnoSem(ws, agno, sem);

        ws.setCursoProfesorList(ContextUtil.getDAO().getCursoProfesorPersistence(ActionUtil.getDBUser()).findCursosMallaJefeArea(
                genericSession.getRut(), ws.getAgnoAct(), ws.getSemAct()));

        return SUCCESS;
    }
}
