package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import infrastructure.support.LoginSessionSupport;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.ActionUtil.retError;
import static infrastructure.util.ActionUtil.retReLogin;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonProfesorUtil;

/**
 * Servicio para gestionar el inicio de sesión de los profesores.
 * 
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorLoginService {

    /**
     * Método para gestionar el inicio de sesión del profesor.
     * 
     * @param action Clase que invoca al servicio.
     * @param sesion El contenedor de la sesión.
     * @param key La clave para acceder a los datos de la sesión.
     * @return El estado de la acción.
     */
    public static String service(ActionCommonSupport action, Map<String, Object> sesion, String key) {
        // Verificar si la sesión y la clave son válidas
        if (action.getSesion().isEmpty() || key == null) {
            return retReLogin();
        }

        // Obtener el objeto LoginSessionSupport de la sesión
        LoginSessionSupport loginSessionSupport = (LoginSessionSupport) sesion.get("loginSessionSupport");

        // Verificar si se encontró una sesión de login válida
        if (loginSessionSupport == null) {
            return retReLogin();
        }

        // Intentar realizar el inicio de sesión
        boolean loginExitoso = CommonProfesorUtil.login(
            loginSessionSupport.getRut(), 
            loginSessionSupport.getPassword(), 
            key, 
            sesion, 
            SystemParametersUtil.INGRESO_REGULAR
        );

        // Si el login falla, agregar un error y retornar el valor de error
        if (!loginExitoso) {
            action.addActionError(action.getText("error.rut.password"));
            return retError();
        }

        // Si el login es exitoso, retornar SUCCESS
        return SUCCESS;
    }
}
