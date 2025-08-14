/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.reservasala;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author rcontreras
 */
public class ReservarService {
    public String service(ActionCommonSupport action, GenericSession genericSession, String dia, Integer modulo, String inicio, String termino, String motivo, String key) throws Exception{
        String retVal = SUCCESS;

        WorkSession ws = genericSession.getWorkSession(key);
        String reserva = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getReserva(ws.getSala().getSalaNum(), dia, modulo+1, inicio, termino);
        if (reserva == null)
        {
           motivo=motivo.trim();
           if ("".equals(motivo)) motivo="Reservada";
           ContextUtil.getDAO().getReservaSalaRepository(ActionUtil.getDBUser()).reservarSala(ws.getSala().getSalaNum(), dia, modulo+1, inicio, termino, motivo, genericSession.getRut());   
        }
        else
        {
            action.addActionError("No es posible hacer la reserva por tope con "+reserva);
            retVal = "tope";
        }

        return retVal;
    }
}
