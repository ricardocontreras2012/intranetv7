/*
 * @(#)AppStaticsUtil.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.util;

import domain.model.WebUser;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Constantes Generales de la aplicación.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class AppStaticsUtil {
    public static final String PDF_MIME = "application/pdf";
    public static final String XLS_MIME = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";


    /**
     * PARAMETROS ACTION
     */
    public static final String ACTION_NOTALLOW = "notAllow";
    public static final String ACTION_RELOGIN_PLUS = "relogin_plus";
    public static final String ACTION_RELOGIN = "relogin";
    public static final String ACTION_ERROR_PLUS = "error_plus";
    public static final String ACTION_ERROR = "error";
    public static final String ACTION_DENIED = "denied";
    public static final String ACTION_EXCEPTION = "exception";

    /**
     * PARAMETROS HIBERNATE
     */
    public static final String HIBERNATE_KEY_CONNECTION_PASSWORD = "hibernate.connection.password";
    public static final String HIBERNATE_KEY_CONNECTION_URL = "hibernate.connection.url";
    public static final String HIBERNATE_KEY_CONNECTION_USERNAME = "hibernate.connection.username";

    /**
     * TIPOS DE USUARIOS DE APLICACION
     */
    public static final Map<String, String> PRIVILEGED_USERS = new LinkedHashMap<String, String>();
    public static final Map<String, String> NORMAL_USERS = new LinkedHashMap<String, String>();
    public static final Map<String, String> SECRETARIAS = new HashMap<String, String>();
    public static final Map<String, String> AUTORIDADES = new HashMap<String, String>();
    public static final Map<String, String> APP_DB_USERS = new LinkedHashMap<String, String>();    

    static {     
        StartUtil start = new StartUtil(PoolLoggingUtil.getConnection());
        
        List<WebUser> usuarios = start.find();

        start.setNormales(NORMAL_USERS, usuarios);
        start.setPlus(PRIVILEGED_USERS, usuarios);
        start.setBD(APP_DB_USERS, usuarios);

        start.setAutoridades(AUTORIDADES, usuarios);
        start.setSecretarias(SECRETARIAS, usuarios);

    }
}
