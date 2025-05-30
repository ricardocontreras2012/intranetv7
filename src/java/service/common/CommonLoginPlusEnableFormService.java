/*
 * @(#)CommonLoginPlusEnableFormService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import java.util.Map;
import infrastructure.support.LoginSessionSupport;
import static infrastructure.util.AppStaticsUtil.PRIVILEGED_USERS;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonLoginPlusEnableFormService {

    /**
     * Method description
     *
     * @param sesion
     */
    public static void service(Map<String, Object> sesion) {
        LoginSessionSupport loginSessionSupport = new LoginSessionSupport();
        loginSessionSupport.setUserTypeMap(PRIVILEGED_USERS);
        sesion.clear();
        sesion.put("loginSessionSupport", loginSessionSupport);
    }
}