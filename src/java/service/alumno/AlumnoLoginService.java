package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.ActionUtil.retError;
import static infrastructure.util.ActionUtil.retReLogin;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonAlumnoUtil;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Servicio encargado de manejar las acciones relacionadas con el inicio de sesión de los estudiantes.
 * 
 * Este servicio valida las credenciales proporcionadas por el estudiante (rut y contraseña) y gestiona la 
 * sesión del usuario. Si las credenciales son correctas, se establece la sesión para el estudiante, 
 * de lo contrario, se retorna un mensaje de error indicando que el inicio de sesión ha fallado.
 * 
 * El servicio también maneja situaciones en las que la sesión o la clave de la sesión no son válidas 
 * o cuando se produce un error durante el proceso de inicio de sesión.
 * 
 * @version 7.0, 24/05/2012
 */
public final class AlumnoLoginService {

    /**
     * Método encargado de procesar el inicio de sesión del estudiante.
     * Este método valida las credenciales proporcionadas (rut y contraseña) y, en caso de ser correctas, 
     * establece la sesión de trabajo para el alumno.
     * 
     * Si la sesión o la clave de sesión son inválidas, se realiza un retorno temprano solicitando 
     * que el usuario vuelva a iniciar sesión. Si las credenciales de inicio de sesión son incorrectas, 
     * se devuelve un mensaje de error. En caso de éxito, se retorna el resultado de "SUCCESS".
     * 
     * @param action La instancia de `ActionCommonSupport` que invoca el servicio, utilizada para agregar 
     *               mensajes de error y recuperar configuraciones relacionadas con la acción.
     * @param sesion
     * @param rut
     * @param passwd
     * @param key La clave de sesión utilizada para identificar y acceder a los datos de la sesión 
     *            del usuario de manera única.
     * @return El estado del intento de inicio de sesión: 
     *         - `"SUCCESS"` si el inicio de sesión fue exitoso. 
     *         - `"error"` si el inicio de sesión falló debido a credenciales incorrectas.
     *         - El resultado del método `retReLogin()` si la sesión o la clave son inválidas.
     */
    public String service(ActionCommonSupport action, Map<String, Object> sesion, Integer rut, String passwd, String key) {
        // Verificación temprana para asegurarse de que la sesión y la clave no sean nulas.
                 
        if (Stream.of(sesion, key, rut, passwd).anyMatch(Objects::isNull)) {
            return retReLogin();
        }       
        
        try {
            // Intentar realizar el inicio de sesión utilizando las credenciales del alumno y la clave de sesión.
            boolean isLoginSuccessful = CommonAlumnoUtil.login(
                rut, 
                passwd, 
                key, 
                sesion, 
                SystemParametersUtil.INGRESO_REGULAR
            );

            // Si el inicio de sesión falla, agregar un mensaje de error y retornar el estado de error.
            if (!isLoginSuccessful) {                
                action.addActionError(action.getText("error.rut.password"));
                return retError(); 
            }

            // Retornar "SUCCESS" si el inicio de sesión fue exitoso.
            return SUCCESS; 

        } catch (Exception e) {
            // Si ocurre una excepción, registrar el error y lanzarla nuevamente para su manejo superior.
            e.printStackTrace();
            throw e;
        }                      
    }
}
