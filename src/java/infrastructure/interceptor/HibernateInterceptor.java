/*
 * @(#)HibernateInterceptor.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.interceptor;

import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.hibernate.Transaction;
import static infrastructure.util.HibernateUtil.closeSession;
import static infrastructure.util.HibernateUtil.getSession;


/*
 * Este interceptor se encarga de asociar cada acción de la aplicación con una sesión de Hibernate.
 * Abre una sesión de Hibernate al inicio de la acción y la cierra al finalizar, asegurando que la sesión
 * esté correctamente gestionada durante el ciclo de vida de la acción.
 *
 */

/**
 * Interceptor que maneja la sesión de Hibernate para cada acción en la aplicación.
 * Abre una sesión de Hibernate antes de ejecutar la acción y la cierra después, asegurando
 * que las transacciones de la base de datos se manejen correctamente, incluso en caso de error.
 * Si ocurre una excepción durante la ejecución de la acción, se hace un rollback de la transacción.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class HibernateInterceptor implements Interceptor {

    // Identificador único para la clase, usado para serialización
    private static final long serialVersionUID = 1L;
    
    // Parámetro que se establece en los archivos de configuración Struts (<param name="toggle">)
    private String toggle;

    /**
     * Método que intercepta la invocación de la acción y asegura que la sesión de Hibernate
     * esté abierta y gestionada correctamente. Si ocurre un error durante la ejecución de la acción,
     * se realiza un rollback de la transacción activa.
     *
     * @param invocation El objeto que contiene la información sobre la acción invocada.
     * @return El resultado de la acción, que puede ser el resultado de la ejecución de la acción
     *         o un error si ocurre una excepción.
     */
    @Override
    public String intercept(ActionInvocation invocation) {
        try {
            // Ejecuta la acción normalmente si no ocurre ningún error
            return invocation.invoke();
        } catch (Exception e) {
            // Si ocurre un error, realiza un rollback de la transacción de Hibernate si está activa
            // Logger.getLogger(this.getClass()).error(FormatUtil.msgLog(":: Invocation.invoke "+ invocation.getProxy().getActionName() + " " + toggle + " causa: " + e.getMessage()));

            // Obtiene la transacción de la sesión de Hibernate
            Transaction tx = getSession(toggle).getTransaction();

            // Si la transacción está activa, realiza un rollback
            if ((tx != null) && tx.isActive()) {
                tx.rollback();
            }

            // Devuelve el resultado de ERROR si ocurre una excepción
            return ERROR;
        } finally {
            // Cierra la sesión de Hibernate, independientemente de si ocurrió un error o no
            closeSession();
        }
    }

    /**
     * Método de destrucción que se invoca cuando el interceptor se destruye.
     * En este caso, no realiza ninguna operación adicional.
     */
    @Override
    public void destroy() {
    }

    /**
     * Método de inicialización que se invoca cuando el interceptor es inicializado.
     * En este caso, no realiza ninguna operación adicional.
     */
    @Override
    public void init() {
    }

    /**
     * Método que obtiene el valor del parámetro "toggle", que se establece en la configuración de Struts.
     *
     * @return El valor de "toggle".
     */
    public String getToggle() {
        return toggle;
    }

    /**
     * Método que establece el valor del parámetro "toggle", que se usa para obtener la sesión de Hibernate.
     *
     * @param toggle El valor del parámetro "toggle".
     */
    public void setToggle(String toggle) {
        this.toggle = toggle;
    }
}
