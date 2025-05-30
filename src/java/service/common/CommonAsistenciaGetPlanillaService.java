package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AsistenciaAlumno;
import domain.model.AsistenciaAlumnoNomina;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 * Clase para obtener la planilla de asistencia de los alumnos.
 */
public final class CommonAsistenciaGetPlanillaService {

    /**
     * Método principal que obtiene la lista de asistencia y otros datos de la sesión.
     *
     * @param genericSession Sesión de trabajo.
     * @param key Llave para acceder a los datos de la sesión.
     * @return Acción de estado.
     */
    public static String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        // Obtener la lista de AsistenciaAlumnoNomina y setearla en el WorkSession
        ws.setAsistenciaAlumnoNominaList(ContextUtil.getDAO().getAsistenciaAlumnoNominaPersistence(ActionUtil.getDBUser()).find(ws.getCurso()));

        // Obtener la lista de AsistenciaAlumno
        ws.setAsistenciaAlumnoList(getAsistencia(ws.getAsistenciaAlumnoNominaList()));

        // Establecer la lista de los alumnos del curso
        ws.setNominaCurso(ws.getCurso().getNominaAlumnos());

        // Registrar el log
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        return SUCCESS;
    }

    /**
     * Método que obtiene la lista de AsistenciaAlumno a partir de la lista de AsistenciaAlumnoNomina.
     *
     * @param lAsistenciaAlumnoNomina Lista de AsistenciaAlumnoNomina.
     * @return Lista de AsistenciaAlumno procesada y ordenada.
     */
    private static List<AsistenciaAlumno> getAsistencia(List<AsistenciaAlumnoNomina> lAsistenciaAlumnoNomina) {
        if (lAsistenciaAlumnoNomina == null) {
            return Collections.emptyList(); // Devuelve una lista vacía
        }

        // Obtener la lista de AsistenciaAlumno única y ordenada por fecha
        return lAsistenciaAlumnoNomina.stream()
                .map(AsistenciaAlumnoNomina::getAsistenciaAlumno) // Extraer la AsistenciaAlumno
                .distinct() // Eliminar duplicados (en caso de que haya)
                .sorted((a1, a2) -> a1.getAsaFecha().compareTo(a2.getAsaFecha())) // Ordenar por fecha
                .collect(Collectors.toList()); // Recoger el resultado en una lista
    }
}
