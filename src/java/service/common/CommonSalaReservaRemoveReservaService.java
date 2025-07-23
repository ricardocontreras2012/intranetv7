/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import com.opensymphony.xwork2.Action;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.HibernateUtil;

/**
 *
 * @author Ricardo
 */
public class CommonSalaReservaRemoveReservaService {

    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        // Iniciar transacción
        HibernateUtil.beginTransaction(ActionUtil.getDBUser());

        try {
            // Filtrar y procesar las reservas seleccionadas
            ws.getReservaList().stream()
                    .filter(reserva -> parameters.containsKey("ck_" + ws.getReservaList().indexOf(reserva)))
                    .map(reserva -> reserva.getRsalCorrel())
                    .forEach(reservaCorrel
                            -> ContextUtil.getDAO().getReservaSalaRepository(ActionUtil.getDBUser()).remove(reservaCorrel)
                    );

            // Confirmar transacción
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            // Revertir transacción en caso de error
            HibernateUtil.rollbackTransaction();
            throw e; // Lanzar la excepción para manejo externo si es necesario
        }

        return Action.SUCCESS;
    }

}
