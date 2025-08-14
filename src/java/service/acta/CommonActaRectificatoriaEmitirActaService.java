package service.acta;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Calificacion;
import domain.model.CalificacionId;
import domain.model.CursoId;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonActaUtil;
import domain.repository.AluCarRepository;

/**
 * Servicio para la emisión del acta rectificatoria de calificaciones.
 * 
 * Este servicio procesa las calificaciones de los estudiantes para emitir un acta rectificatoria. 
 * Las calificaciones son clasificadas según el tipo de alumno (Bachillerato, Movilidad, Intercambio, Regular) 
 * y luego se crea un acta correspondiente para cada grupo de estudiantes.
 * 
 * El servicio también actualiza las calificaciones en la base de datos y genera un registro de auditoría.
 * 
 * @author Ricardo Contreras S.
 * @version 1.0, 2023
 */
public final class CommonActaRectificatoriaEmitirActaService {

    /**
     * Método principal para procesar la emisión del acta rectificatoria de calificaciones.
     *
     * Este método procesa las calificaciones de los estudiantes, las categoriza por tipo de alumno,
     * y luego genera un acta para cada tipo de estudiante. Las calificaciones modificadas se almacenan 
     * en la base de datos y se registra el evento.
     * 
     * @param action Acción que ejecuta el servicio, utilizada para gestionar errores y mensajes.
     * @param genericSession La sesión del usuario que contiene la información de la sesión de trabajo.
     * @param map Mapa con las nuevas calificaciones de los estudiantes.
     * @param key Llave única para acceder a la sesión del trabajo.
     * 
     * @return El estado de la acción. Siempre retorna el valor de éxito (SUCCESS).
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, Map<String, String[]> map, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        String user = ActionUtil.getDBUser();
        AluCarRepository Repository = ContextUtil.getDAO().getAluCarRepository(user);
        CursoId cursoId = ws.getCurso().getId();

        // Listas para almacenar las calificaciones categorizadas por tipo de alumno
        List<Calificacion> alumnoRegular = new ArrayList<>();
        List<Calificacion> alumnoMovilidad = new ArrayList<>();
        List<Calificacion> alumnoIntercambio = new ArrayList<>();
        List<Calificacion> alumnoBachiller = new ArrayList<>();

        // Procesar las calificaciones y asignarlas a las listas correspondientes
        ws.getActaRectificatoriaList().forEach(calificacion -> {
            String[] tmp = map.get("nota_" + calificacion.getId().getCalRut());
            BigDecimal nota = new BigDecimal(tmp[0]);

            // Verifica si la calificación ha cambiado
            if (nota.compareTo(calificacion.getCalNotaFin()) != 0) {
                String tipoAlumno = Repository.tipoAlumno(calificacion.getAluCar().getId());
                calificacion.setCalNotaFin(nota);

                // Categoriza la calificación según el tipo de alumno
                categorizarAlumno(tipoAlumno, calificacion, alumnoBachiller, alumnoMovilidad, alumnoIntercambio, alumnoRegular);
            }
        });

        // Procesar las listas de calificaciones y generar el acta para cada grupo de alumnos
        procesarListas(cursoId, "B", alumnoBachiller, user);
        procesarListas(cursoId, "M", alumnoMovilidad, user);
        procesarListas(cursoId, "I", alumnoIntercambio, user);
        procesarListas(cursoId, "R", alumnoRegular, user);

        // Finaliza la acción y añade un mensaje de éxito
        action.clearErrorsAndMessages();
        action.addActionMessage(action.getText("message.acta.emitida"));

        // Registra la emisión del acta
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        return SUCCESS;
    }

    /**
     * Categoriza un alumno según su tipo y asigna la calificación a la lista correspondiente.
     * 
     * @param tipoAlumno El tipo de alumno (Bachillerato, Movilidad, Intercambio, Regular).
     * @param calificacion La calificación del alumno.
     * @param alumnoBachiller Lista de calificaciones de alumnos Bachillerato.
     * @param alumnoMovilidad Lista de calificaciones de alumnos de Movilidad.
     * @param alumnoIntercambio Lista de calificaciones de alumnos de Intercambio.
     * @param alumnoRegular Lista de calificaciones de alumnos Regulares.
     */
    private void categorizarAlumno(String tipoAlumno, Calificacion calificacion,
                                          List<Calificacion> alumnoBachiller, List<Calificacion> alumnoMovilidad,
                                          List<Calificacion> alumnoIntercambio, List<Calificacion> alumnoRegular) {
        Consumer<Calificacion> addCalificacion = cal -> {
            switch (tipoAlumno) {
                case "B":
                    alumnoBachiller.add(cal);
                    break;
                case "M":
                    alumnoMovilidad.add(cal);
                    break;
                case "I":
                    alumnoIntercambio.add(cal);
                    break;
                case "R":
                    alumnoRegular.add(cal);
                    break;
                default:
                    // Maneja el caso donde el tipo de alumno no es reconocido
                    break;
            }
        };
        addCalificacion.accept(calificacion);
    }

    /**
     * Procesa las listas de calificaciones y emite el acta correspondiente para cada grupo de alumnos.
     * 
     * @param id El identificador del curso.
     * @param tipo El tipo de acta a emitir (Bachiller, Movilidad, Intercambio, Regular).
     * @param nomina La lista de calificaciones de alumnos de un tipo específico.
     * @param user El usuario que realiza la operación, utilizado para acceder a la base de datos.
     */
    private void procesarListas(CursoId id, String tipo, List<Calificacion> nomina, String user) {
        if (!nomina.isEmpty()) {
            int folio = createActa(id.getCurAsign(), id.getCurElect(), id.getCurCoord(),
                                   id.getCurSecc(), id.getCurAgno(), id.getCurSem(), id.getCurComp(),
                                   tipo, user);
            nomina.forEach(calificacion -> crearCalificacionActa(folio, calificacion, user));
        }
    }

    /**
     * Crea un nuevo acta y obtiene su número de folio.
     * 
     * @param asign El código de asignatura.
     * @param elect El código de especialidad.
     * @param coord El código de coordinación.
     * @param secc El código de sección.
     * @param agno El año académico del acta.
     * @param sem El semestre académico del acta.
     * @param tel El código del régimen.
     * @param tipo El tipo de acta a emitir (Bachillerato, Movilidad, Intercambio, Regular).
     * @param user El usuario que realiza la operación.
     * @return El número de folio del acta creada.
     */
    private int createActa(Integer asign, String elect, String coord, Integer secc,
                                  Integer agno, Integer sem, String tel, String tipo, String user) {
        Integer folio = CommonActaUtil.getFolio(user);
        ContextUtil.getDAO().getActaCalificacionRepository(user)
                   .crearActa(folio, agno, sem, asign, elect, coord, secc, tel, "R", "E", tipo);
        return folio;
    }

    /**
     * Inserta una calificación en el acta correspondiente.
     * 
     * @param folio El número de folio del acta.
     * @param calificacion La calificación a insertar en el acta.
     * @param user El usuario que realiza la operación.
     */
    private void crearCalificacionActa(Integer folio, Calificacion calificacion, String user) {
        CalificacionId calificacionId = calificacion.getId();
        ContextUtil.getDAO().getActaCalificacionNominaRepository(user)
                   .insertCalificacion(folio, calificacionId.getCalAgno(), calificacionId.getCalSem(),
                                       calificacionId.getCalRut(), calificacionId.getCalCodCar(),
                                       calificacionId.getCalAgnoIng(), calificacionId.getCalSemIng(),
                                       calificacion.getCalNotaFin());
    }
}
