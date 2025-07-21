/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import domain.model.Solicitud;
import session.GenericSession;
import session.WorkSession;
import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author rcontreras
 */
public class CommonPracticaViewSolicitudService {

    public String service(GenericSession genericSession, String key, Integer pos) {
        WorkSession ws = genericSession.getWorkSession(key);

        Solicitud sol = ws.getSolicitudList().get(pos);

        ws.setSolicitud(sol);
        sol.setSolicitudAttachList(ContextUtil.getDAO().getSolicitudAttachPersistence(ActionUtil.getDBUser()).find(sol));

        ws.setPractica(ContextUtil.getDAO().getPracticaPersistence(ActionUtil.getDBUser()).find(sol.getSolFolio()));

        if (ContextUtil.getDAO().getPracticaPersistence(ActionUtil.getDBUser()).getPracticaxInscribir(ws.getSolicitud().getAluCar()).intValue() == ws.getPractica().getAsignatura().getAsiCod().intValue()) {                       
            sol.setSolMotivo("Cumple requisitos");
        } else {
            sol.setSolMotivo("NO Cumple requisitos");
        }

        return SUCCESS;
    }
}
