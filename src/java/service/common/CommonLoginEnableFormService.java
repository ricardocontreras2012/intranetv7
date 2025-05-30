/*
 * @(#)CommonLoginEnableFormService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import java.util.Map;
import infrastructure.support.LoginSessionSupport;
import static infrastructure.util.AppStaticsUtil.NORMAL_USERS;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonLoginEnableFormService {

    /**
     * Habilita formulario de LogIn.
     *
     * @param sesion Sesión de la aplicación.
     */
    public static void service(Map<String, Object> sesion) {
        LoginSessionSupport lSession = new LoginSessionSupport();
        lSession.setUserTypeMap(NORMAL_USERS);
        sesion.clear();
        sesion.put("loginSessionSupport", lSession);
    }
}
