package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import domain.repository.AsistenciaAlumnoNominaPersistence;
import domain.repository.AsistenciaAlumnoPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 * Servicio para eliminar la asistencia de los alumnos.
 */
public final class CommonAsistenciaRemoveAsistenciaService {

    /**
     * Elimina la asistencia de los alumnos especificados en los parámetros.
     *
     * @param genericSession Sesión de trabajo.
     * @param parameters Mapa de parámetros del formulario.
     * @param key Llave para acceder a los datos de la sesión.
     * @return Estado de la acción.
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession workSession = genericSession.getWorkSession(key);

        AsistenciaAlumnoPersistence asistenciaAlumnoPersistence = ContextUtil.getDAO()
                .getAsistenciaAlumnoPersistence(ActionUtil.getDBUser());
        AsistenciaAlumnoNominaPersistence asistenciaAlumnoNominaPersistence = ContextUtil.getDAO()
                .getAsistenciaAlumnoNominaPersistence(ActionUtil.getDBUser());

        // Filtramos las asistencias que deben eliminarse usando Stream
        workSession.getAsistenciaAlumnoList().stream()
                .filter(asistencia -> parameters.containsKey("ck_" + workSession.getAsistenciaAlumnoList().indexOf(asistencia)))
                .map(asistencia -> asistencia.getAsaCorrel())
                .forEach(correl -> {
                    asistenciaAlumnoNominaPersistence.delete(correl);
                    asistenciaAlumnoPersistence.delete(correl);
                });

        // Actualizamos la lista de AsistenciaAlumno en la sesión
        workSession.setAsistenciaAlumnoList(asistenciaAlumnoPersistence.find(workSession.getCurso()));

        // Registrar el log
        LogUtil.setLogCurso(genericSession.getRut(), workSession.getCurso());

        return SUCCESS;
    }
}
