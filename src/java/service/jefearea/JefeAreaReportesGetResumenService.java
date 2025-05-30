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
 * @author Ricardo Contreras S.
 */
public class JefeAreaReportesGetResumenService {

    /**
     *
     * @param genericSession
     * @param key
     * @param agno
     * @param sem
     * @return
     */
    public static String service(GenericSession genericSession, String key, Integer agno, Integer sem) {
        WorkSession ws = genericSession.getWorkSession(key);

        if (agno == null || sem == null) {
            agno = ws.getAgnoAct();
            sem = ws.getSemAct();
        }

        ws.setResumenCurso(ContextUtil.getDAO().getCursoPersistence(ActionUtil.getDBUser()).findResumenReportes(
                genericSession.getRut(), agno, sem));

        ws.setAgnoAct(agno);
        ws.setSemAct(sem);

        return SUCCESS;
    }
}
