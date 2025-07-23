/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util.common;

import static java.lang.Integer.valueOf;
import domain.repository.ParametroRepository;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Administrador
 */
public class CommonUtil {

    public static void setAgnoSem(WorkSession ws, Integer agno, Integer sem) {
        if (agno == null || sem == null) {
            if (ws.getAgnoAct() == null || ws.getSemAct() == null) {
                setAgnoSemAct(ws);
                return;
            } else {
                agno = ws.getAgnoAct();
                sem = ws.getSemAct();
            }
        }

        ws.setAgnoAct(agno);
        ws.setSemAct(sem);
    }

    public static void setAgnoSemAct(WorkSession ws) {
        ParametroRepository parametroRepository = ContextUtil.getDAO().getParametroRepository(ActionUtil.getDBUser());

        ws.setAgnoAct(valueOf(parametroRepository.find("agno_act").getParValor()));
        ws.setSemAct(valueOf(parametroRepository.find("sem_act").getParValor()));
    }

    public static String getEmailUsach(String email) {
        String aux = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getEmailUsach(email);
        return "null".equals(aux) ? "" : aux;
    }
}
