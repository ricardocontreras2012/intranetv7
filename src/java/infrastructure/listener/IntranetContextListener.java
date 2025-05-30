/*
 * @(#)IntranetContextListener.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import infrastructure.util.ContextUtil;
import static infrastructure.util.ContextUtil.getInstance;
import infrastructure.util.FontsPDFUtil;
import infrastructure.util.HibernateUtil;
import infrastructure.util.PoolLoggingUtil;
import infrastructure.util.SystemParametersUtil;

/**
 * Este escuchador (listener) se encarga de escuchar los eventos de inicialización 
 * y destrucción del contexto de la aplicación web. Su función principal es configurar 
 * el entorno de la aplicación durante la inicialización y liberar los recursos 
 * cuando el contexto de la aplicación se destruye.
 *
 * @autor Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class IntranetContextListener implements ServletContextListener {

    /**
     * Este método se llama cuando el contexto de la aplicación web se inicializa.
     * Aquí se configuran los parámetros necesarios para el entorno de la aplicación 
     * y se guarda la instancia de `ContextUtil` en el `ServletContext`.
     *
     * @param event El evento de inicialización del contexto de la aplicación.
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("Iniciando Contexto en el listener...");
        
        // Recupera el contexto del servlet a partir del evento
        ServletContext servletContext = event.getServletContext();
        
        // Obtiene la instancia única de ContextUtil (utilidad del contexto de la aplicación)
        ContextUtil contextoIntranet = getInstance();
        
        // Establece el atributo 'contextoIntranet' en el contexto del servlet para ser utilizado más adelante
        servletContext.setAttribute("contextoIntranet", contextoIntranet);
        
        FontsPDFUtil fontsUtil = new FontsPDFUtil(servletContext);
        servletContext.setAttribute("fontsUtil", fontsUtil);
        
        System.out.println("Contexto Iniciado");        
        System.out.println(SystemParametersUtil.ACCESO_WEB);
    }

    /**
     * Este método se llama cuando el contexto de la aplicación web se destruye.
     * En este punto, se realizan las tareas de limpieza necesarias, como cerrar 
     * el pool de conexiones, la sesión de Hibernate y desregistrar los drivers JDBC 
     * que fueron cargados por la aplicación web.
     *
     * @param event El evento de destrucción del contexto de la aplicación.
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("Eliminando Contexto en el listener !!!");
        
        // Cierra el pool de conexiones
        PoolLoggingUtil.closePool();
        
        // Cierra la fábrica de sesiones de Hibernate
        HibernateUtil.closeSessionFactory();
        
        // Obtiene el cargador de clases actual del hilo
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        
        // Recupera todos los drivers JDBC registrados con el DriverManager
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        
        // Itera a través de todos los drivers y desregistra aquellos que fueron registrados 
        // por el cargador de clases de la aplicación web
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            
            // Si el driver fue registrado por el cargador de clases de la aplicación web, lo desregistra
            if (driver.getClass().getClassLoader() == cl) {
                try {
                    System.out.println("Deregistrando driver JDBC {}");
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                    System.out.println("Error al desregistrar el driver JDBC {}");
                }
            } else {
                // Si el driver no fue registrado por el cargador de clases de la aplicación web, no se desregistra
                System.out.println("No se desregistra el driver JDBC {} ya que no pertenece al ClassLoader de la aplicación web");
            }
        }
        
        System.out.println("Contexto eliminado !!!");
    }
}
