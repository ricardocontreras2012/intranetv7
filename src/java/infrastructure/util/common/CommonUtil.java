/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util.common;

import static java.lang.Integer.valueOf;
import domain.repository.ParametroPersistence;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Administrador
 */
public class CommonUtil {

    public static void setAgnoSemAct(WorkSession ws) {
        ParametroPersistence parametroPersistence = ContextUtil.getDAO().getParametroPersistence(ActionUtil.getDBUser());

        ws.setAgnoAct(valueOf(parametroPersistence.find("agno_act").getParValor()));
        ws.setSemAct(valueOf(parametroPersistence.find("sem_act").getParValor()));
    }

    public static String getEmailUsach(String email) {
        String aux = ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getEmailUsach(email);
        return "null".equals(aux)?"":aux;
    }
}
