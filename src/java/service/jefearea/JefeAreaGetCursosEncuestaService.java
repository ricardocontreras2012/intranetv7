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

/**
 *
 * @author Administrador
 */
public class JefeAreaGetCursosEncuestaService {

    public static String service(GenericSession genericSession, String key, Integer agno, Integer sem) { 
        WorkSession ws = genericSession.getWorkSession(key);      
        
        if (agno == null || sem == null) {
            agno = ws.getAgnoAct();
            sem = ws.getSemAct();
        }

        ws.setCursoProfesorList(ContextUtil.getDAO().getCursoProfesorPersistence(ActionUtil.getDBUser()).findCursosMallaJefeArea(
                genericSession.getRut(), agno, sem));
        ws.setAgnoAct(agno);
        ws.setSemAct(sem);

        return SUCCESS;
    }
}
