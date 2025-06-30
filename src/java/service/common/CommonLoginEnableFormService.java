/*
 * @(#)CommonLoginEnableFormService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import java.util.Map;
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
     * @return 
     */
    public static Map<String, String> service() {
        return NORMAL_USERS;        
    }
}
