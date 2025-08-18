package service.common;

import domain.model.Administrativo;
import java.util.HashMap;
import java.util.Map;
import static org.apache.struts2.ServletActionContext.getRequest;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.ActionUtil.retError;
import static infrastructure.util.AppStaticsUtil.ACTION_NOTALLOW;
import infrastructure.util.ActionUtil;
import static infrastructure.util.ActionUtil.retReLogin;
import infrastructure.util.ContextUtil;
import java.util.Objects;
import java.util.stream.Stream;
import domain.repository.AdministrativoRepository;

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
     * @param rut
     * @param passwd
     * @param userType
     * @param key La clave para acceder a los datos de la sesión.
     * @return El estado de la acción.
     */
    public String service(ActionCommonSupport action, Map<String, Object> sesion,  Integer rut, String passwd, String userType, String key) {
        // Verificación de parámetros necesarios
        if (Stream.of(sesion, key, rut, passwd, userType).anyMatch(Objects::isNull)) {
            return retReLogin();
        }

        AdministrativoRepository admRepo = ContextUtil.getDAO().getAdministrativoRepository(ActionUtil.getDBUser());
        Administrativo administrativo = admRepo.find(rut, passwd);
        
        // Verificamos si el administrativo existe
        if (administrativo == null) {
            action.addActionError(action.getText("error.rut.password"));
            return retError();
        }

        // Creamos la sesión de trabajo
        GenericSession genericSession = new GenericSession(userType, rut, passwd, 0);
        genericSession.setAdministrativo(administrativo);
        
        // Verificación de tipo de usuario
        if (administrativo.is(userType)) {
            // Configuración exitosa de la sesión
            admRepo.setLastLogin(rut);
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
    private void getComplemento(GenericSession genericSession, Administrativo administrativo) {
        genericSession.setDv(administrativo.getAdmDv());
        genericSession.setPaterno(administrativo.getAdmPaterno());
        genericSession.setMaterno(administrativo.getAdmMaterno());
        genericSession.setNombres(administrativo.getAdmNombre());
        genericSession.setNombre(administrativo.getNombreStd());
        genericSession.setNombreMensaje(administrativo.getNombreMensaje());
        genericSession.setEmail(administrativo.getAdmEmail());
    }
}
