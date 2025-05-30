package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Solicitud;
import domain.repository.SolicitudPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Servicio para eliminar solicitudes del alumno.
 * Este servicio permite eliminar solicitudes de un alumno si cumplen ciertas condiciones.
 * 
 * @version 1.0, 23/12/2020
 * @author Ricardo Contreras S.
 */
public final class AlumnoSolicitudRemoveSolicitudService {

    /**
     * Elimina las solicitudes seleccionadas por el usuario, si estan en el estado Generada.
     * 
     * @param genericSession La sesión genérica que contiene la sesión de trabajo del alumno.
     * @param parameters El mapa de parámetros del formulario.
     * @param key La clave para acceder a la sesión de trabajo.
     * @return El estado de la acción (SUCCESS si el proceso fue exitoso).
     */
    public static String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        // Recupera la sesión de trabajo del alumno
        WorkSession ws = genericSession.getWorkSession(key);

        // Verifica que el tipo de usuario sea "AL" (Alumno)
        if ("AL".equals(genericSession.getUserType())) {
            SolicitudPersistence solicitudPersistence = ContextUtil.getDAO().getSolicitudPersistence(ActionUtil.getDBUser());
            List<Solicitud> solicitudList = ws.getSolicitudList();

            // Inicia la transacción
            beginTransaction(ActionUtil.getDBUser());

            // Itera a través de la lista de solicitudes y elimina las seleccionadas
            IntStream.range(0, solicitudList.size())
                    .filter(i -> parameters.get("ck_" + i) != null)  // Filtra las solicitudes marcadas
                    .mapToObj(i -> solicitudList.get(i))  // Obtiene la solicitud correspondiente
                    .filter(solicitud -> "GEN".equals(solicitud.getEstadoSolicitud().getEsolDesCorta()))  // Filtra las solicitudes con estado "GEN"
                    .forEach(solicitud -> {
                        // Log de la solicitud eliminada
                        LogUtil.setLog(genericSession.getRut(), "Folio=" + solicitud.getSolFolio());
                        // Elimina la solicitud de la base de datos
                        solicitudPersistence.makeTransient(solicitud);
                    });

            // Commit de la transacción
            commitTransaction();
        }

        return SUCCESS;
    }
}
