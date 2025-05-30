/*
 * @(#)AyudanteLoginService.java
 *
 * Copyright (c) 2023 FAE-USACH
 */
package service.ayudante;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import infrastructure.support.LoginSessionSupport;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.ActionUtil.retError;
import static infrastructure.util.ActionUtil.retReLogin;
import infrastructure.util.common.CommonAyudanteUtil;

/**
 * Servicio encargado de manejar el proceso de login del ayudante, validando las credenciales
 * y estableciendo la sesión de trabajo para el ayudante, con la carga de cursos y configuración
 * necesaria para su acceso al sistema.
 * 
 * @version 7, 24/05/2012
 * @author Ricardo Contreras S.
 */
public final class AyudanteLoginService {

    /**
     * Método encargado de manejar la autenticación del ayudante, verificando las credenciales
     * proporcionadas y configurando la sesión correspondiente en el sistema.
     *
     * @param action Clase que invoca al servicio, utilizada para gestionar mensajes y errores.
     * @param sesion Mapa que contiene la sesión del usuario.
     * @param key Llave para acceder a los datos específicos de la sesión.
     * @return El estado de la acción: SUCCESS si la autenticación fue exitosa, o un código de error.
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
        boolean loginExitoso = CommonAyudanteUtil.login(
            loginSessionSupport.getRut(), 
            loginSessionSupport.getPassword(), 
            key, 
            sesion
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
