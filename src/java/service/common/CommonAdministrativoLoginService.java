package service.common;

import domain.model.Administrativo;
import java.util.HashMap;
import java.util.Map;
import static org.apache.struts2.ServletActionContext.getRequest;
import domain.repository.AdministrativoPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.LoginSessionSupport;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.ActionUtil.retError;
import static infrastructure.util.ActionUtil.retReLogin;
import static infrastructure.util.AppStaticsUtil.ACTION_NOTALLOW;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Servicio para gestionar el inicio de sesión del administrativo.
 * 
 * @version 7, 24/05/2012
 */
public final class CommonAdministrativoLoginService {

    /**
     * Método para gestionar el inicio de sesión del administrativo.
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

        LoginSessionSupport loginSession = (LoginSessionSupport) sesion.get("loginSessionSupport");

        if (loginSession == null) {
            return retReLogin();
        }

        // Recuperamos los datos de la sesión
        Integer rut = loginSession.getRut();
        String password = loginSession.getPassword();
        String userType = loginSession.getUserType();
        AdministrativoPersistence admPersistence = ContextUtil.getDAO().getAdministrativoPersistence(ActionUtil.getDBUser());
        Administrativo administrativo = admPersistence.find(rut, password);
        
        // Verificamos si el administrativo existe
        if (administrativo == null) {
            action.addActionError(action.getText("error.rut.password"));
            return retError();
        }

        // Creamos la sesión de trabajo
        GenericSession genericSession = new GenericSession(userType, rut, password, 0);
        genericSession.setAdministrativo(administrativo);
        
        // Verificación de tipo de usuario
        if (administrativo.is(userType)) {
            // Configuración exitosa de la sesión
            admPersistence.setLastLogin(rut);
            getComplemento(genericSession, administrativo);

            // Guardamos la sesión
            sesion.put("genericSession", genericSession);
            genericSession.setSessionMap(new HashMap<>());
            genericSession.getSessionMap().put(key, new WorkSession(userType));
            genericSession.setLastLogin(administrativo.getAdmLastLogin());

            // Configuramos el tiempo de inactividad de la sesión
            getRequest().getSession().setMaxInactiveInterval(1800);
            return userType;
        }

        // Si no tiene permisos, mostramos un mensaje de error
        action.addActionError(action.getText("error.not.allow"));
        return ACTION_NOTALLOW;
    }

    /**
     * Completa la sesión con la información adicional del administrativo.
     * 
     * @param genericSession La sesión de trabajo.
     * @param administrativo El objeto administrativo.
     */
    private static void getComplemento(GenericSession genericSession, Administrativo administrativo) {
        genericSession.setDv(administrativo.getAdmDv());
        genericSession.setPaterno(administrativo.getAdmPaterno());
        genericSession.setMaterno(administrativo.getAdmMaterno());
        genericSession.setNombres(administrativo.getAdmNombre());
        genericSession.setNombre(administrativo.getNombreStd());
        genericSession.setNombreMensaje(administrativo.getNombreMensaje());
        genericSession.setEmail(administrativo.getAdmEmail());
    }
}
