package service.asistencia;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import domain.repository.AsistenciaAlumnoNominaRepository;
import domain.repository.AsistenciaAlumnoRepository;

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

        AsistenciaAlumnoRepository asistenciaAlumnoRepository = ContextUtil.getDAO()
                .getAsistenciaAlumnoRepository(ActionUtil.getDBUser());
        AsistenciaAlumnoNominaRepository asistenciaAlumnoNominaRepository = ContextUtil.getDAO()
                .getAsistenciaAlumnoNominaRepository(ActionUtil.getDBUser());

        // Filtramos las asistencias que deben eliminarse usando Stream
        workSession.getAsistenciaAlumnoList().stream()
                .filter(asistencia -> parameters.containsKey("ck_" + workSession.getAsistenciaAlumnoList().indexOf(asistencia)))
                .map(asistencia -> asistencia.getAsaCorrel())
                .forEach(correl -> {
                    asistenciaAlumnoNominaRepository.delete(correl);
                    asistenciaAlumnoRepository.delete(correl);
                });

        // Actualizamos la lista de AsistenciaAlumno en la sesión
        workSession.setAsistenciaAlumnoList(asistenciaAlumnoRepository.find(workSession.getCurso()));

        // Registrar el log
        LogUtil.setLogCurso(genericSession.getRut(), workSession.getCurso());

        return SUCCESS;
    }
}
