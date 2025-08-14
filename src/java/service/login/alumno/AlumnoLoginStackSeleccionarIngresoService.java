package service.login.alumno;

import domain.model.AluCar;
import java.util.List;
import session.AlumnoSession;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonAlumnoUtil;

/**
 * Servicio encargado de gestionar el inicio de sesión de un alumno, 
 * considerando los registros de matrícula encontrados en el sistema.
 * 
 * Este servicio permite que un alumno se autentique si se encuentra un único registro. 
 * Si existen múltiples registros, se almacenan en la sesión y se notifica al usuario para que 
 * seleccione el registro adecuado.
 * 
 * El flujo de inicio de sesión depende de la cantidad de registrosencontrados:
 * 1. Si se encuentra un solo registro de matrícula, se procede con el inicio de sesión directamente.
 * 2. Si se encuentran varios registros, se almacenan en la sesión y el usuario debe seleccionar uno.
 * 
 * @version 1.0, 24/05/2022
 */
public class AlumnoLoginStackSeleccionarIngresoService {

    /**
     * Método para gestionar el inicio de sesión de un alumno según los registros de matrícula encontrados.
     * 
     * El método realiza lo siguiente:
     * - Recupera los registros asociados alumno-carrera utilizando su identificador único (rut).
     * - Si se encuentra un solo registro, procede a autenticar al alumno.
     * - Si se encuentran múltiples registros, estos se almacenan en la sesión y se indica que el alumno debe 
     *   seleccionar uno para proceder con el inicio de sesión.
     * 
     * @param genericSession La sesión genérica que contiene información sobre la sesión actual del sistema.
     * @param alumnoSession La sesión del alumno, que contiene los datos específicos del alumno durante el proceso.
     * @param key La clave única de sesión utilizada para acceder y modificar los datos de la sesión.
     * @return El resultado del proceso:
     *         - `"stack"` si se encontró un solo registro y el inicio de sesión se realizó con éxito.
     *         - `"multiplesIngresos"` si se encontraron varios registros, y el alumno debe elegir uno.
     */
    public String service(GenericSession genericSession, AlumnoSession alumnoSession, String key) {
        // Inicializar el valor de retorno como "stack", que indica que se procederá con un solo registro de matrícula
        String retValue = "stack";

        // Recuperar la lista de registros de matrícula del alumno
        List<AluCar> aluCarList = ContextUtil.getDAO()
                .getAluCarRepository(ActionUtil.getDBUser())
                .find(genericSession.getRut());

        // Verificar si se encontraron registros
        if (aluCarList != null && !aluCarList.isEmpty()) {
            // Si hay un único registro, proceder con el inicio de sesión
            if (aluCarList.size() == 1) {
                AluCar aluCar = aluCarList.get(0); // Obtener el único registro
                // Realizar el inicio de sesión utilizando los datos del registro encontrado
                CommonAlumnoUtil.login(genericSession, alumnoSession, aluCar, key);
            } else {
                // Si hay múltiples registros, almacenarlos en la sesión para que el alumno los seleccione
                genericSession.getWorkSession(key).setAluCarList(aluCarList);
                retValue = "multiplesIngresos"; // Indicar que el alumno debe seleccionar un registro
            }
        }

        // Retornar el valor adecuado según el número de registros encontrados
        return retValue;
    }
}
