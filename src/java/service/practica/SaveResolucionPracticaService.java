/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.practica;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Practica;
import domain.model.Solicitud;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 *
 * @author rcontreras
 */
public class SaveResolucionPracticaService {

    public String service(GenericSession genericSession, String key, String estado, String respuesta, Integer agno, Integer sem) {        
        if (!"S".equals(estado)) {
            WorkSession ws = genericSession.getWorkSession(key);
            Solicitud sol = ws.getSolicitud();
            Practica practica = ws.getPractica();
            sol.getEstadoSolicitud().setEsolCod(40);
            sol.setSolRespuesta(respuesta);
            practica.getId().setPraAgno(agno);
            practica.getId().setPraSem(sem);

            switch (estado) {

                case "A":
                    sol.setSolResolucion("A");
                    practica.setPraEstado("A");
                    break;

                case "R":
                    sol.setSolResolucion("R");
                    practica.setPraEstado("R");
                    break;
                default:
                    break;
            }

            beginTransaction(ActionUtil.getDBUser());
            ContextUtil.getDAO().getSolicitudRepository(ActionUtil.getDBUser()).makePersistent(sol);
            ContextUtil.getDAO().getPracticaRepository(ActionUtil.getDBUser()).resolver(practica);
            commitTransaction();

        }
        return SUCCESS;
    }
}
