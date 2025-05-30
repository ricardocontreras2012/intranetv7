package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.CursoId;
import domain.model.ModuloHorario;
import java.util.List;
import java.util.stream.Collectors;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getDate;
import static infrastructure.util.SystemParametersUtil.DATE_FORMAT;
import infrastructure.util.common.CommonHorarioUtil;

/**
 * Servicio para generar el reporte de horarios de un profesor.
 * Este servicio obtiene la fecha actual, el horario de los módulos y prepara la lista de módulos para el reporte.
 * 
 * @version 1.0, 23/12/2024
 * @author Ricardo Contreras S.
 */
public final class ProfesorReporteAddReporteService {

    /**
     * Servicio que genera un reporte con la fecha actual y los horarios de los módulos asociados al curso del profesor.
     * 
     * @param genericSession La sesión genérica que contiene la sesión de trabajo del profesor.
     * @param key La clave para acceder a la sesión de trabajo.
     * @return El estado de la acción (SUCCESS si el proceso fue exitoso).
     * @throws Exception Si el servicio genera una excepción.
     */
    public static String service(GenericSession genericSession, String key) throws Exception {
        // Recupera la sesión de trabajo del profesor
        WorkSession ws = genericSession.getWorkSession(key);

        // Establece la fecha actual en la sesión
        ws.setFechaActual(getDate(DATE_FORMAT));

        // Recupera el ID del curso del profesor
        CursoId id = ws.getCurso().getId();

        // Establece la lista de módulos de horario en la sesión
        List<ModuloHorario> moduloHorarioList = CommonHorarioUtil.getModuloHorarioDocencia(id.getCurAgno(), id.getCurSem());
        ws.setModuloHorarioList(moduloHorarioList);

        // Establece los módulos combinados con los días en la sesión
        ws.setModulos(getModulos(moduloHorarioList));

        return SUCCESS;
    }

    /**
     * Genera una lista de módulos combinados con los días de la semana para el reporte.
     * 
     * @param modList Lista de módulos de horario a combinar con los días.
     * @return Lista de cadenas combinadas de días y códigos de módulos.
     */
    private static List<String> getModulos(List<ModuloHorario> modList) {
        // Obtiene los días de la semana y los módulos combinados usando Streams
        return ContextUtil.getDiaList().stream()
                .flatMap(dia -> modList.stream()
                        .map(mod -> dia.getDiaCod() + mod.getId().getModCod())) // Combina día y módulo
                .collect(Collectors.toList()); // Recoge los resultados en una lista
    }
}
