/*
 * @(#)HibernateUtil.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.util;

import java.util.HashMap;
import java.util.Map;
import org.hibernate.*;
import static org.hibernate.EmptyInterceptor.INSTANCE;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import static infrastructure.util.AppStaticsUtil.APP_DB_USERS;
import static infrastructure.util.AppStaticsUtil.HIBERNATE_KEY_CONNECTION_PASSWORD;
import static infrastructure.util.AppStaticsUtil.HIBERNATE_KEY_CONNECTION_URL;
import static infrastructure.util.AppStaticsUtil.HIBERNATE_KEY_CONNECTION_USERNAME;
import static infrastructure.util.LogUtil.logExceptionMessage;
import static infrastructure.util.LogUtil.logInfo;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class HibernateUtil {

    /**
     * Interceptor class
     */
    private static final String INTERCEPTOR_CLASS = "hibernate.util.interceptor_class";

    /**
     * Session Factories
     */
    private static final Map<String, SessionFactory> SESSION_FACTORIES = new HashMap<String, SessionFactory>();

    /**
     * threadlocal.
     */
    @SuppressWarnings("rawtypes")
    private static final ThreadLocal THREAD_TRANSACTION = new ThreadLocal();

    /**
     * threadlocal.
     */
    @SuppressWarnings("rawtypes")
    private static final ThreadLocal THREAD_SESSION = new ThreadLocal();

    /**
     * threadlocal.
     */
    @SuppressWarnings("rawtypes")
    private static final ThreadLocal THREAD_INTERCEPTOR = new ThreadLocal();

    /**
     * configuration.
     */
    private static Configuration configuration;

    /**
     * Create the initial SessionFactory from hibernate.xml.cfg or JNDI). ####
     * Use this Function to initialize Hibernate! ####
     *
     * @param offlineMode true=hibernate.cfg.xml , false=JNDI
     */
    public static void configure(boolean offlineMode) {
        logInfo("HibernateUtil.Configure() - Trying to initialize Hibernate.");

        try {
            // Use hibernate.cfg.xml (true) or JNDI (false)
            setOfflineMode(offlineMode);
            getSessionFactories();
        } catch (Throwable x) {

            // We have to catch Throwable, otherwise we will miss
            // NoClassDefFoundError and other subclasses of Error
            logExceptionMessage(x);

            throw new ExceptionInInitializerError(x);
        }
    }

    /**
     * Method description
     */
    @SuppressWarnings("rawtypes")
    private static void getSessionFactories() {
        APP_DB_USERS.values()
                .stream()
                .distinct() // Elimina duplicados automáticamente
                .forEach(userDB -> processUsersGetSessionFactories(userDB));
    }

    /**
     * Method description
     *
     * @param userType
     */
    private static void processUsersGetSessionFactories(String userType) {
        logInfo("Configurando session factory to " + userType + "...");

        try {
            HibernatePropertiesUtil hibernatePropertiesUtil = new HibernatePropertiesUtil(userType);

            configuration = new Configuration();
            configuration.configure("config/hibernate/hibernate" + userType + ".cfg.xml");
            configuration.setProperty(HIBERNATE_KEY_CONNECTION_URL, hibernatePropertiesUtil.getUrl());
            configuration.setProperty(HIBERNATE_KEY_CONNECTION_USERNAME, hibernatePropertiesUtil.getUsername());
            configuration.setProperty(HIBERNATE_KEY_CONNECTION_PASSWORD, hibernatePropertiesUtil.getPassword());
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            SessionFactory factory = configuration.buildSessionFactory(builder.build());

            SESSION_FACTORIES.put(userType, factory);
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the SessionFactory used for this static class. If offlineMode has
     * been set then we use hibernate.cfg.xml to create sessionfactory, if not
     * then we use sessionfactory bound to JNDI.
     *
     * @param user
     * @return SessionFactory
     */
    private static SessionFactory getSessionFactory(String user) {
        return SESSION_FACTORIES.get(user);
    }

    /**
     * Method description
     */
    @SuppressWarnings("rawtypes")
    private static void processUsersCloseSessionFactories() {
        try {
            APP_DB_USERS.values()
                    .stream()
                    .distinct() // Elimina duplicados automáticamente
                    .forEach(userDB -> {
                        SessionFactory sessionFactory = SESSION_FACTORIES.get(userDB);
                        System.out.println("Eliminando SessionFactory: " + userDB + " :: " + sessionFactory);
                        sessionFactory.close();
                    });

        } catch (Exception x) {
            throw new HibernateException(
                    "HibernateUtil.closeSessionFactory() - Error destroying the current SessionFactory", x);
        }
    }

    /**
     * Method description
     *
     * @throws HibernateException
     */
    public static void closeSessionFactory() throws HibernateException {
        synchronized (SESSION_FACTORIES) {
            try {
                processUsersCloseSessionFactories();
                configuration = null;
                logInfo("HibernateUtil.closeSessionFactory() - Destroy the current SessionFactory.");
            } catch (Exception x) {
                throw new HibernateException(
                        "HibernateUtil.closeSessionFactory() - Error destroying the current SessionFactory", x);
            }
        }
    }

    /**
     * Use hibernate.cfg.xml (true) to create sessionfactory or bound
     * sessionfactory to JNDI (false)
     *
     * @param mode
     */
    private static void setOfflineMode(boolean mode) {
        if (mode) {
            logInfo("HibernateUtil.setOfflineMode() - Setting mode to hibernate.cfg.xml .");
        } else {
            logInfo("HibernateUtil.setOfflineMode() - Setting mode to JNDI.");
        }
    }

    /**
     * Returns the original Hibernate configuration.
     *
     * @return Configuration
     */
    public static Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Retrieves the current Session local to the thread. If no Session is open,
     * opens a new Session for the running thread.
     *
     * @param user
     * @return Session
     * @throws InfrastructureExceptionUtil
     */
    @SuppressWarnings("unchecked")
    public static Session getSession(String user) throws InfrastructureExceptionUtil {
        Session s = (Session) THREAD_SESSION.get();

        try {
            if (s == null) {
                s = getSessionFactory(user).openSession();
                THREAD_SESSION.set(s);
            }
        } catch (Exception x) {
            logExceptionMessage(x);

            throw new InfrastructureExceptionUtil("HibernateUtil.getSession() - Error retrieving/creating a session.",
                    x);
        }

        return s;
    }

    /**
     * Closes the Session local to the thread.
     *
     * @throws InfrastructureExceptionUtil
     */
    @SuppressWarnings("unchecked")
    public static void closeSession() throws InfrastructureExceptionUtil {
        try {
            Session s = (Session) THREAD_SESSION.get();

            THREAD_SESSION.set(null);

            if ((s != null) && s.isOpen()) {
                s.close();
            }
        } catch (HibernateException x) {
            x.printStackTrace();

            throw new InfrastructureExceptionUtil("HibernateUtil.closeSession() - Error closing the session.", x);
        }
    }

    /**
     * Start a new database transaction.
     *
     * @param user
     * @throws InfrastructureExceptionUtil
     */
    @SuppressWarnings("unchecked")
    public static void beginTransaction(String user) throws InfrastructureExceptionUtil {
        Transaction tx = (Transaction) THREAD_TRANSACTION.get();

        try {
            if (tx == null) {
                tx = getSession(user).beginTransaction();

                if (tx.isActive()) {
                    THREAD_TRANSACTION.set(tx);
                }
            }
        } catch (HibernateException x) {
            throw new InfrastructureExceptionUtil(
                    "HibernateUtil.beginTransaction() - Error starting a new database transaction.", x);
        }
    }

    /**
     * Commit the database transaction.
     *
     * @throws InfrastructureExceptionUtil
     */
    @SuppressWarnings("unchecked")
    public static void commitTransaction() throws InfrastructureExceptionUtil {
        Transaction tx = (Transaction) THREAD_TRANSACTION.get();

        try {
            if ((tx != null) && !tx.wasCommitted() && !tx.wasRolledBack()) {

                //if ((tx != null) && !tx.wasCommitted() && !tx.wasRolledBack()) {
                // LogUtil.logInfo("HibernateUtil.commitTransaction() - Committing database transaction of
                // this thread.");
                tx.commit();
            }

            THREAD_TRANSACTION.set(null);
        } catch (HibernateException x) {
            rollbackTransaction();

            throw new InfrastructureExceptionUtil(
                    "HibernateUtil.commitTransaction() - Error commiting the database transaction.", x);
        }
    }

    /**
     * Rollback the database transaction.
     *
     * @throws InfrastructureExceptionUtil
     */
    @SuppressWarnings("unchecked")
    public static void rollbackTransaction() throws InfrastructureExceptionUtil {
        Transaction tx = (Transaction) THREAD_INTERCEPTOR.get();

        try {
            THREAD_TRANSACTION.set(null);

            if ((tx != null) && !tx.wasCommitted() && !tx.wasRolledBack()) {
                logInfo(
                        "HibernateUtil.rollbackTransaction() - Tyring to rollback database transaction of this thread"
                        + '.');
                tx.rollback();
            }
        } catch (HibernateException x) {
            throw new InfrastructureExceptionUtil(
                    "HibernateUtil.rollbackTransaction() - Error rolling back the database transaction.", x);
        } finally {
            closeSession();
        }
    }

    /**
     * Disconnect and return Session from current Thread.
     *
     * @param user
     * @return Session the disconnected Session
     * @throws InfrastructureExceptionUtil
     */
    @SuppressWarnings("unchecked")
    public static Session disconnectSession(String user) throws InfrastructureExceptionUtil {
        logInfo("HibernateUtil.disconnectSession() - Disconnecting Session from current Thread.");

        Session session = getSession(user);

        try {
            THREAD_SESSION.set(null);

            if (session.isConnected() && session.isOpen()) {
                session.disconnect();
            }
        } catch (HibernateException x) {
            throw new InfrastructureExceptionUtil(
                    "HibernateUtil.disconnectSession() - Error disconnecting session from current thread.", x);
        }

        return session;
    }

    /**
     * Register a Hibernate interceptor with the current thread. Every Session
     * opened is opened with this interceptor after registration. Has no effect
     * if the current Session of the thread is already open, effective on next
     * close()/getSession().
     *
     * @param interceptor
     */
    @SuppressWarnings("unchecked")
    public static void registerInterceptor(Interceptor interceptor) {
        THREAD_INTERCEPTOR.set(interceptor);
    }

    /**
     * Get Hibernate interceptor.
     *
     */
    /**
     * Resets global interceptor to default state.
     */
    public static void resetInterceptor() {
        logInfo(
                "HibernateUtil.resetInterceptor() - Resetting global interceptor to configuration setting");
        setInterceptor(configuration, null);
    }

    /**
     * Either sets the given interceptor on the configuration or looks it up
     * from configuration if null.
     *
     * @param configuration
     * @param interceptor
     */
    @SuppressWarnings("rawtypes")
    private static void setInterceptor(Configuration configuration, Interceptor interceptor) {
        String interceptorName = configuration.getProperty(INTERCEPTOR_CLASS);

        if ((interceptor == null) && (interceptorName != null)) {
            try {
                logInfo("HibernateUtil.setInterceptor() - Configuring interceptor.");

                Class interceptorClass = HibernateUtil.class.getClassLoader().loadClass(interceptorName);

                interceptor = (Interceptor) interceptorClass.newInstance();
            } catch (Exception x) {
                throw new RuntimeException("HibernateUtil.setInterceptor() - Error, could not configure interceptor: "
                        + interceptorName, x);
            }
        }

        if (interceptor != null) {
            configuration.setInterceptor(interceptor);
        } else {
            configuration.setInterceptor(INSTANCE);
        }
    }
}
