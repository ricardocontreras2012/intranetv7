/*
 * @(#)AppStaticsUtil.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.util;

import domain.model.WebUser;
import java.util.Collections;
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
    public static final String HIBERNATE_PASSWORD = "hibernate.connection.password";
    public static final String HIBERNATE_URL = "hibernate.connection.url";
    public static final String HIBERNATE_USERNAME = "hibernate.connection.username";

    /**
     * TIPOS DE USUARIOS DE APLICACION
     */
    public static final Map<String, String> PRIVILEGED_USERS;
    public static final Map<String, String> NORMAL_USERS;
    public static final Map<String, String> SECRETARIAS;
    public static final Map<String, String> AUTORIDADES;
    public static final Map<String, String> APP_DB_USERS;

    static {
        StartUtil start = new StartUtil(PoolLoggingUtil.getConnection());
        List<WebUser> usuarios = start.find();

        Map<String, String> priv = new LinkedHashMap<>();
        Map<String, String> norm = new LinkedHashMap<>();
        Map<String, String> secre = new HashMap<>();
        Map<String, String> auto = new HashMap<>();
        Map<String, String> appdb = new LinkedHashMap<>();

        start.setNormales(norm, usuarios);
        start.setPlus(priv, usuarios);
        start.setBD(appdb, usuarios);
        start.setAutoridades(auto, usuarios);
        start.setSecretarias(secre, usuarios);

        PRIVILEGED_USERS = Collections.unmodifiableMap(priv);
        NORMAL_USERS = Collections.unmodifiableMap(norm);
        SECRETARIAS = Collections.unmodifiableMap(secre);
        AUTORIDADES = Collections.unmodifiableMap(auto);
        APP_DB_USERS = Collections.unmodifiableMap(appdb);
    }   
}
