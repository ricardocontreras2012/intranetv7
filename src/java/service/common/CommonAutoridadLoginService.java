package service.common;

import domain.model.Profesor;
import session.*;
import infrastructure.support.LoginSessionSupport;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.ActionUtil.retError;
import static infrastructure.util.ActionUtil.retReLogin;
import static infrastructure.util.AppStaticsUtil.ACTION_NOTALLOW;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

import java.util.HashMap;
import java.util.Map;

import static org.apache.struts2.ServletActionContext.getRequest;
import domain.repository.ProfesorPersistence;
import infrastructure.support.LaborSupport;

/**
 * Servicio para gestionar el inicio de sesión de la autoridad.
 *
 * @version 7, 24/05/2012
 */
public final class CommonAutoridadLoginService {

    /**
     * Método para gestionar el inicio de sesión de la autoridad.
     *
     * @param action Clase que invoca al servicio.
     * @param sesion El contenedor de la sesión.
     * @param key La clave para acceder a los datos de la sesión.
     * @return El estado de la acción.
     */
    public static String service(ActionCommonSupport action, Map<String, Object> sesion, String key) {
        // Verificación de parámetros necesarios
        if (sesion == null || key == null) {
            return retReLogin();
        }

        LoginSessionSupport loginSessionSupport = (LoginSessionSupport) sesion.get("loginSessionSupport");

        if (loginSessionSupport == null) {
            return retReLogin();
        }

        // Recuperamos los datos de la sesión
        Integer rut = loginSessionSupport.getRut();
        String password = loginSessionSupport.getPassword();
        String userType = loginSessionSupport.getUserType();

        ProfesorPersistence profesorPersistence = ContextUtil.getDAO().getProfesorPersistence(ActionUtil.getDBUser());
        Profesor profesor = profesorPersistence.find(rut, password);

        // Verificamos si el profesor existe
        if (profesor == null) {
            action.addActionError(action.getText("error.rut.password"));
            return retError();
        }

        // Creamos la sesión de trabajo
        GenericSession genericSession = new GenericSession(userType, rut, password, 0);
        genericSession.setProfesor(profesor);

        // Verificación de permisos según el tipo de usuario
        String retValue = ACTION_NOTALLOW; // Aseguramos que retValue se inicialice correctamente

        if (rut != null && userType != null && LaborSupport.is(rut, userType)) {
            switch (userType) {
                case "JC": {
                    sesion.put("jefeCarreraSession", new JefeCarreraSession());
                    sesion.put("profesorSession", new ProfesorSession());
                    break;
                }
                default:
            }
            
            retValue = userType;

        } else {
            action.addActionError(action.getText("error.not.allow"));
        }

        if (!retValue.equals(ACTION_NOTALLOW)) {
            // Configuración adicional de la sesión
            configurarSessionData(genericSession, profesor, userType, key, profesorPersistence);
            action.getSesion().put("genericSession", genericSession);
        }

        return retValue;
    }

    /**
     * Configura los datos adicionales en la sesión del administrativo.
     *
     * @param genericSession La sesión de trabajo.
     * @param profesor El objeto profesor.
     * @param userType El tipo de usuario.
     * @param key La clave de sesión.
     * @param profesorPersistence El objeto de persistencia de profesor.
     */
    private static void configurarSessionData(GenericSession genericSession, Profesor profesor, String userType, String key, ProfesorPersistence profesorPersistence) {
        WorkSession ws = new WorkSession(userType);

        genericSession.setSessionMap(new HashMap<>());
        genericSession.getSessionMap().put(key, ws);

        ProfesorSession profesorSession = new ProfesorSession();
        genericSession.setProfesorSession(profesorSession);
        profesorSession.setProfesor(profesor);

        // Configuración del profesor en la sesión
        genericSession.setEmail(profesor.getProfEmail());
        genericSession.setNombre(profesor.getNombreStd());
        genericSession.setNombreMensaje(profesor.getNombreMensaje());

        // Actualizar el último login del profesor
        profesorPersistence.setLastLogin(profesor.getProfRut());
        genericSession.setLastLogin(profesor.getProfLastLogin());

        // Configuramos el tiempo de inactividad de la sesión
        getRequest().getSession().setMaxInactiveInterval(1800);
    }
}
