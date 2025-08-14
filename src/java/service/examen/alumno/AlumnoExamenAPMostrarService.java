package service.examen.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.Asignatura;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la obtención y procesamiento del sorteo de exámenes asociados a un alumno de AP.
 * 
 * Este servicio recupera una lista de exámenes (asignaturas) de una base de datos, los procesa,
 * elimina duplicados y los ordena por código de asignatura antes de almacenarlos en la sesión de trabajo.
 */
public class AlumnoExamenAPMostrarService {

    /**
     * Procesa los exámenes del alumno y los almacena en la sesión de trabajo.
     * 
     * @param genericSession La sesión genérica que contiene la sesión de trabajo del alumno.
     * @param key La clave para acceder a la sesión de trabajo específica.
     * @return El resultado de la acción (SUCCESS si el proceso fue exitoso).
     */
    public String service(GenericSession genericSession, String key) {
        // Recupera la sesión de trabajo usando la clave proporcionada
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();

        // Obtiene la cadena de exámenes asociados al alumno desde la base de datos
        String examenStr = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getExamenAP(aluCar.getId());

        // Si hay exámenes disponibles (cadena no vacía)
        if (!examenStr.isEmpty()) {
            // Procesa la cadena de exámenes usando Streams para eliminar duplicados, mapear y ordenar
            List<Asignatura> retValue = Arrays.stream(examenStr.split("\\s*,\\s*"))
                    .map(s -> s.split(">"))
                    .filter(splited -> splited.length == 2) // Filtra solo los elementos con formato válido
                    .map(splited -> {
                        try {
                            Asignatura asignatura = new Asignatura();
                            asignatura.setAsiCod(Integer.parseInt(splited[0])); // Código de la asignatura
                            asignatura.setAsiNom(splited[1]); // Nombre de la asignatura
                            return asignatura;
                        } catch (NumberFormatException e) {
                            // En caso de error en el formato numérico, se omite el examen
                            System.err.println("Error al parsear el código de asignatura: " + splited[0]);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull) // Filtra cualquier asignatura nula
                    .collect(Collectors.toSet()) // Elimina duplicados usando un Set
                    .stream()
                    .sorted(Comparator.comparingInt(Asignatura::getAsiCod)) // Ordena las asignaturas por código
                    .collect(Collectors.toList()); // Recoge las asignaturas ordenadas en una lista

            // Guarda la lista de asignaturas procesadas en la sesión de trabajo
            ws.setAsignaturaList(retValue);
        }

        return SUCCESS;
    }
}
