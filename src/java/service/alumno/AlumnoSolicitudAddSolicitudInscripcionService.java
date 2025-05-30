package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import domain.model.Solicitud;
import domain.model.TmotivoSolicitudInscripcion;
import static java.lang.Integer.parseInt;
import java.util.Map;
import java.util.Optional;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;

/**
 * Servicio encargado de agregar solicitudes de inscripción de un alumno.
 * 
 * Este servicio maneja el proceso de creación de una solicitud de inscripción 
 * y la vinculación de cursos seleccionados por el alumno, así como los motivos de 
 * inscripción asociados a cada curso. Además, registra la acción en el sistema 
 * para mantener el seguimiento y estado de la solicitud.
 * 
 * El flujo del servicio incluye:
 * 1. Guardar la solicitud inicial.
 * 2. Registrar los cursos seleccionados y sus respectivos motivos.
 * 3. Actualizar la solicitud con un resumen de los cursos y motivos.
 * 4. Registrar la acción en los logs del sistema.
 * 
 * @version 1.0, 24/05/2023
 */
public final class AlumnoSolicitudAddSolicitudInscripcionService {

    /**
     * Método principal para agregar una solicitud de inscripción de un alumno.
     * 
     * Este método guarda la solicitud y los cursos seleccionados por el alumno, 
     * incluyendo los motivos de inscripción y cualquier otro detalle relacionado.
     * 
     * El proceso se divide en varias transacciones para asegurar la integridad de 
     * los datos y el registro adecuado de la información.
     * 
     * @param genericSession La sesión genérica que contiene información sobre la sesión del usuario.
     * @param parameters El mapa con los parámetros enviados desde la interfaz de usuario.
     * @param key La llave utilizada para acceder a la sesión del usuario.
     * @return El resultado de la operación, en este caso siempre devuelve SUCCESS.
     */
    public static String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        // Obtener la sesión de trabajo para acceder a los datos actuales del usuario
        WorkSession ws = genericSession.getWorkSession(key);        
        String user = ActionUtil.getDBUser();

        // Iniciar transacción para guardar la solicitud
        beginTransaction(user);
        Solicitud solicitud = ws.getSolicitud();
        ContextUtil.getDAO().getSolicitudPersistence(user).save(solicitud);
        commitTransaction();

        // Iniciar transacción para guardar los cursos seleccionados y motivos de inscripción
        beginTransaction(user);
        StringBuilder acum = new StringBuilder();  // Usar StringBuilder para acumular el texto

        // Procesar los parámetros usando lambda y streams
        parameters.entrySet().stream()
            .filter(entry -> entry.getKey().startsWith("curso_"))  // Filtrar los campos que son de curso
            .forEach(entry -> {
                int row = parseInt(entry.getKey().substring(entry.getKey().lastIndexOf('_') + 1));
                Integer cursoPos = Integer.parseInt(parameters.get("curso_" + row)[0]);
                Integer motivo = Integer.parseInt(parameters.get("motivo_" + row)[0]);
                String otro = parameters.get("otro_" + row)[0];

                // Obtener el curso y guardar la relación con la solicitud
                Curso curso = ws.getCursoSolicitudList().get(cursoPos);
                ContextUtil.getDAO().getSolicitudInscripcionPersistence(user)
                        .doSave(solicitud.getSolFolio(), curso.getId(), motivo, otro);

                // Acumular la información de los cursos y motivos
                acum.append(curso.getNombreFull())
                    .append("->")
                    .append(getMotivoDes(ws, motivo))
                    .append(" :: ");
            });
        commitTransaction();

        // Iniciar transacción para actualizar la solicitud con los cursos registrados
        beginTransaction(user);
        ContextUtil.getDAO().getSolicitudPersistence(user).modify(solicitud.getSolFolio(), acum.toString());
        commitTransaction();

        // Registrar el log de la acción
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }

    /**
     * Método para obtener la descripción del motivo de inscripción a partir de su código.
     * 
     * Este método busca el código de motivo en la lista de motivos disponibles en 
     * la sesión de trabajo y devuelve su descripción.
     * 
     * @param ws La sesión de trabajo que contiene la lista de motivos.
     * @param codigo El código del motivo de inscripción.
     * @return La descripción del motivo correspondiente al código proporcionado.
     */
    private static String getMotivoDes(WorkSession ws, int codigo) {
        Optional<TmotivoSolicitudInscripcion> motivoOpt = ws.getTmotivoSolicitudInscripcionList().stream()
            .filter(tmotivo -> tmotivo.getTmsiCod() == codigo)
            .findFirst();
        return motivoOpt.map(TmotivoSolicitudInscripcion::getTmsiDes).orElse("");
    }
}
