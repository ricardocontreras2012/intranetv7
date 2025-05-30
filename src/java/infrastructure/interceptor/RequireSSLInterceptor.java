/*
 * @(#)RequireSSLInterceptor.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import javax.servlet.ServletRequest;
import static org.apache.struts2.StrutsStatics.HTTP_REQUEST;
import static infrastructure.util.LogUtil.logInfo;


/*
 * Este interceptor fuerza a que las conexiones de la aplicación utilicen el protocolo HTTPS (conexión segura).
 * Si una solicitud no es segura (HTTP), la acción no será invocada y se registrará un error.
 *
 */

/**
 * Interceptor que asegura que las solicitudes a la aplicación se realicen utilizando el protocolo HTTPS.
 * Si la solicitud no utiliza HTTPS, se registra un error y no se invoca la acción solicitada.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class RequireSSLInterceptor extends AbstractInterceptor {

    // Identificador único para la clase, usado para serialización
    private static final long serialVersionUID = 1L;

    /**
     * Método que intercepta la invocación de una acción y verifica si la solicitud se realiza a través de HTTPS.
     * Si la solicitud no es segura, se devuelve un error y no se ejecuta la acción.
     *
     * @param invocation El objeto que contiene la información sobre la acción invocada.
     * @return El resultado de la acción si la solicitud es segura (HTTPS), de lo contrario, un error.
     * @throws java.lang.Exception Si ocurre un error durante la ejecución de la acción.
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        // Variable que define el valor por defecto cuando la solicitud es segura
        String retValue = "https";

        // Verifica si la solicitud se realiza a través de HTTPS
        if (((ServletRequest) invocation.getInvocationContext().get(HTTP_REQUEST)).isSecure()) {
            // Si la solicitud es segura, invoca la acción
            retValue = invocation.invoke();
        } else {
            // Si la solicitud no es segura, se registra un error
            logInfo("ERROR:: Protocolo Inválido" + invocation.getAction().getClass());
        }

        // Retorna el resultado de la invocación, o el error si la solicitud no es segura
        return retValue;
    }
}
