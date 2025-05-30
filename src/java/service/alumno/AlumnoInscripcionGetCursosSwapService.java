package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.CursoId;
import domain.model.Inscripcion;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import session.AlumnoSession;

/**
 * Esta clase proporciona un servicio para gestionar las inscripciones de un
 * alumno en el sistema, permitiendo realizar intercambios de cursos.
 *
 * El servicio se utiliza para actualizar la lista de cursos disponibles para un
 * alumno, basado en la selección de inscripciones ya existentes en su registro.
 * Se actualiza la sesión de trabajo del alumno para reflejar el cambio en la
 * lista de cursos según el curso seleccionado en la interfaz de usuario.
 *
 * El proceso está diseñado para gestionar la selección de un solo curso a la
 * vez y actualizar la sesión de trabajo con los cursos intercambiables
 * relacionados con ese curso en particular.
 */
public class AlumnoInscripcionGetCursosSwapService {

    /**
     * Este método maneja el intercambio de cursos para un alumno, basado en la
     * selección de inscripciones que el alumno realiza a través de la interfaz
     * de usuario.
     *
     * Al recibir la solicitud de acción, el servicio localiza la inscripción
     * seleccionada por el alumno, extrae el curso asociado, y actualiza la
     * sesión de trabajo con una nueva lista de cursos intercambiables,
     * relacionada con el curso seleccionado.
     *
     * La sesión de trabajo se maneja de manera que se mantiene consistente con
     * la acción del usuario, y todos los cambios se persisten para esa sesión
     * en particular.
     *
     * @param action Una instancia de `ActionCommonSupport`, que contiene
     * información y utilidades comunes para la acción que se está ejecutando.
     * Esto ayuda a gestionar el contexto de la solicitud, como la base de
     * datos.
     * @param genericSession Una instancia de `GenericSession`, que mantiene el
     * estado de la sesión actual, permitiendo acceder a la información del
     * alumno y a la sesión de trabajo activa.
     * @param as
     * @param parameters Mapa de parámetros enviados por la solicitud HTTP, que
     * contiene los datos sobre qué inscripciones fueron seleccionadas por el
     * alumno (en este caso, las casillas de verificación que corresponden a las
     * inscripciones).
     * @param key Clave única de la sesión de trabajo asociada al alumno. Este
     * parámetro se utiliza para identificar la sesión de trabajo dentro del
     * sistema y acceder a los datos pertinentes.
     * @return El estado de la acción, en este caso un valor de `"SUCCESS"`, que
     * indica que la operación de intercambio de cursos se realizó
     * correctamente.
     */
    public static String service(ActionCommonSupport action, GenericSession genericSession,
            AlumnoSession as, Map<String, String[]> parameters, String key) {

        // Recupera la sesión de trabajo asociada al alumno usando la clave proporcionada.
        WorkSession ws = genericSession.getWorkSession(key);

        // Obtiene el objeto AluCar, que es la representación de los datos del alumno 
        // y su lista de inscripciones (inscripciones en cursos).
        AluCar aluCar = ws.getAluCar();

        // Obtiene la lista de inscripciones asociadas al alumno.
        List<Inscripcion> insList = aluCar.getInsList();

        // Utiliza un Stream para encontrar la primera inscripción seleccionada por el alumno.
        Optional<Inscripcion> selectedInscription = insList.stream()
                .filter(inscription -> parameters.containsKey("ck_" + insList.indexOf(inscription)))
                .findFirst();

        // Si se encuentra una inscripción seleccionada, actualiza la sesión de trabajo.
        selectedInscription.ifPresent(inscription -> {
            // Obtiene el ID del curso de la inscripción seleccionada.
            CursoId id = inscription.getCurso().getId();

            //System.out.println("curso====" + inscription.getCurso().getCodigo("-"));

            // Establece el curso de la sesión de trabajo con el curso de la inscripción seleccionada.
            ws.setCurso(inscription.getCurso());

            // Actualiza la lista de cursos disponibles para el alumno en función del curso seleccionado.
            as.setCursosSwap(ContextUtil.getDAO()
                    .getInscripcionPersistence(ActionUtil.getDBUser())
                    .getCursosSwap(aluCar, id));

        });

        LogUtil.setLog(genericSession.getRut());

        // Retorna "SUCCESS", indicando que la operación se completó correctamente.
        return SUCCESS;
    }
}
