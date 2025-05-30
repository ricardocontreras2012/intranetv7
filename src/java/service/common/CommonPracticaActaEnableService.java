/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Asignatura;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonUtil;

/**
 *
 * @author rcontreras
 */
public class CommonPracticaActaEnableService {

    public static String service(GenericSession genericSession, Integer agno, Integer sem, Integer practica, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        List<Asignatura> asignList = null;

        switch (genericSession.getUserType()) {
            case "SM":
                asignList = ContextUtil.getDAO().getPracticaPersistence(ActionUtil.getDBUser()).getPracticasSecretaria(genericSession.getRut());
                break;
            case "CP":
                asignList = ContextUtil.getDAO().getPracticaPersistence(ActionUtil.getDBUser()).getPracticasCoordinador(genericSession.getRut());
                break;
        }

        ws.setAsignaturaList(asignList);

        if (agno == null || sem == null) {
            CommonUtil.setAgnoSemAct(ws);

            agno = ws.getAgnoAct();
            sem = ws.getSemAct();
            practica = ws.getAsignaturaList().get(0).getAsiCod();
        }

        ws.setPracticaList(ContextUtil.getDAO().getPracticaPersistence(ActionUtil.getDBUser()).getNominaxCalificar(practica, agno, sem));

        ws.setAgnoAct(agno);
        ws.setSemAct(sem);
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }
}
