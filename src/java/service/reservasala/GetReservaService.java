/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.reservasala;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author rcontreras
 */
public class GetReservaService {
    public String service(GenericSession genericSession, Integer reserva, String key) {
        genericSession.getWorkSession(key).setReserva(ContextUtil.getDAO().getReservaSalaRepository(ActionUtil.getDBUser()).find(reserva));
        return SUCCESS;
    }
}
