/*
 * @(#)AuthenticationInterceptor.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import static com.opensymphony.xwork2.util.TextParseUtil.commaDelimitedStringToSet;
import domain.model.WebUserAction;
import static java.util.Collections.emptySet;
import java.util.Set;
import static org.apache.struts2.ServletActionContext.getRequest;
import session.GenericSession;
import infrastructure.util.ContextUtil;
import static infrastructure.util.LogUtil.logInfo;
import infrastructure.util.AppStaticsUtil;
import java.util.Optional;


/*
 * Este interceptor verifica si la acción invocada tiene una sesión válida asociada.
 * En caso de no haber sesión activa o válida, redirige al usuario a una página de autenticación.
 *
 */
/**
 * Interceptor encargado de verificar que el usuario tenga una sesión válida
 * para ejecutar la acción solicitada. Si el usuario no tiene una sesión válida
 * o no está autorizado para ejecutar la acción, se redirige a una página de
 * error de acceso.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AuthenticationInterceptor extends AbstractInterceptor {

    // Constantes para los resultados de la verificación de sesión
    private static final String AUTHENTICATION_REQUIRED_RESULT = "authentication_required";
    private static final String ACTION_DENIED = "access_denied";

    // Identificador único para la clase, usado para serialización
    private static final long serialVersionUID = 1L;

    // Conjunto de acciones que no requieren autenticación
    private Set<String> excludeActions = emptySet();

    // Identificador de Struts utilizado en el contexto
    private String strutsId;

    /**
     * Este método intercepta la acción invocada y valida si el usuario tiene
     * una sesión válida. Si la acción requiere autenticación, pero no se
     * encuentra una sesión válida, se redirige a la página de autenticación. Si
     * el usuario no tiene acceso a la acción, se redirige a la página de acceso
     * denegado.
     *
     * @param invocation El objeto que contiene la información sobre la acción
     * que se invocó.
     * @return El resultado de la acción, que puede ser un acceso permitido,
     * denegado o redirección a autenticación.
     * @throws java.lang.Exception Si ocurre un error durante la interceptación
     * de la acción.
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext.getContext().put("strutsId", strutsId);
        String actionName = invocation.getProxy().getActionName();

        if (excludeActions.contains(actionName)) {
            return invocation.invoke();
        }

        return Optional.ofNullable((GenericSession) invocation.getInvocationContext().getSession().get("genericSession"))
                .map(session -> {
                    String userType = session.getUserType();
                    WebUserAction wua = ContextUtil.getDAO()
                            .getLogActionPersistence(AppStaticsUtil.APP_DB_USERS.get(userType))
                            .find(userType, actionName);

                    if (wua != null) {
                        try {
                            return invocation.invoke();
                        } catch (Exception e) {
                            throw new RuntimeException("Error en la invocación de la acción", e);
                        }
                    } else {
                        System.out.println(">>> action=" + actionName + "->" + userType);
                        return ACTION_DENIED;
                    }
                })
                .orElseGet(() -> {
                    logInfo("ERROR DE SESION IP: " + getRequest().getRemoteAddr() + " " + actionName);
                    return AUTHENTICATION_REQUIRED_RESULT;
                });
    }

    /**
     * Establece las acciones que deben ser excluidas de la verificación de
     * autenticación. Estas acciones no requieren que el usuario esté
     * autenticado.
     *
     * @param values Cadena de texto con las acciones separadas por comas.
     */
    public void setExcludeActions(String values) {
        if (values != null) {
            // Convierte la cadena de acciones en un conjunto de acciones a excluir
            this.excludeActions = commaDelimitedStringToSet(values);
        }
    }

    /**
     * Establece el ID de Struts para el contexto de la acción.
     *
     * @param strutsId El ID de Struts.
     */
    public void setStrutsId(String strutsId) {
        this.strutsId = strutsId;
    }
}
