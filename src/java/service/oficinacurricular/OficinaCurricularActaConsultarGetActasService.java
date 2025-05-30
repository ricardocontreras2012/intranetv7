/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.oficinacurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.repository.ActaCalificacionPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class OficinaCurricularActaConsultarGetActasService {

    public static String service(GenericSession genericSession, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        ActaCalificacionPersistence actaPersistence
                = ContextUtil.getDAO().getActaCalificacionPersistence(ActionUtil.getDBUser());

        ws.setActas(actaPersistence.findActasxEstado(ws.getAgnoAct(), ws.getSemAct(), ws.getFlag()));

        return SUCCESS;
    }
}
