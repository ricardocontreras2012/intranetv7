/*
 * @(#)CachingHeadersInterceptor.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import static com.opensymphony.xwork2.util.TextParseUtil.commaDelimitedStringToSet;
import static java.util.Collections.emptySet;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import static org.apache.struts2.StrutsStatics.HTTP_RESPONSE;


/*
 * Este interceptor agrega cabeceras HTTP para evitar que el navegador almacene en caché las respuestas de la aplicación.
 * Se usa principalmente para garantizar que la información mostrada a los usuarios no sea obsoleta o incorrecta debido al almacenamiento en caché del navegador.
 *
 */

/**
 * Interceptor que agrega cabeceras HTTP para evitar el almacenamiento en caché de las respuestas en el navegador del usuario.
 * Configura cabeceras como "Cache-control", "Pragma" y "Expires" para asegurar que las respuestas no sean almacenadas en caché.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
@SuppressWarnings("rawtypes")
public final class CachingHeadersInterceptor extends AbstractInterceptor {

    // Identificador único para la clase, usado para serialización
    private static final long serialVersionUID = 1L;
    
    // Conjunto de acciones para las cuales no se aplicarán las cabeceras de caché
    private Set excludeActions = emptySet();

    /**
     * Este método intercepta la ejecución de la acción y configura las cabeceras de la respuesta HTTP para evitar
     * el almacenamiento en caché en el navegador. Si la acción no está en la lista de acciones excluidas, se agregan
     * las cabeceras correspondientes.
     *
     * @param invocation El objeto que contiene la información sobre la acción invocada.
     * @return El resultado de la acción, que se continúa con la ejecución después de agregar las cabeceras.
     * @throws java.lang.Exception Si ocurre un error durante la interceptación de la acción.
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        // Se obtiene el contexto de la acción y la respuesta HTTP
        ActionContext context = invocation.getInvocationContext();
        HttpServletResponse response = (HttpServletResponse) context.get(HTTP_RESPONSE);

        // Si la respuesta no es nula y la acción no está excluida, se configuran las cabeceras
        if (response != null && !excludeActions.contains(invocation.getProxy().getActionName())) {
            // Se agregan las cabeceras para evitar el almacenamiento en caché
            response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "-1");
            response.setHeader("Vary", "*");
        }

        // Se continúa con la ejecución de la acción
        return invocation.invoke();
    }

    /**
     * Método que obtiene el conjunto de acciones excluidas de la configuración de cabeceras.
     *
     * @return El conjunto de acciones que no recibirán las cabeceras de caché.
     */
    @SuppressWarnings("rawtypes")
    public Set getExcludeActions() {
        return excludeActions;
    }

    /**
     * Establece las acciones para las cuales no se aplicarán las cabeceras de caché.
     * Las acciones especificadas en la cadena de texto se convierten en un conjunto de acciones excluidas.
     *
     * @param values Cadena de texto con los nombres de las acciones separadas por comas.
     */
    public void setExcludeActions(String values) {
        if (values != null) {
            // Convierte la cadena de acciones en un conjunto de acciones excluidas
            this.excludeActions = commaDelimitedStringToSet(values);
        }
    }
}
