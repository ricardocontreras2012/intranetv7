/*
 * @(#)EnableFormService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.login;

import java.util.Map;
import static infrastructure.util.AppStaticsUtil.NORMAL_USERS;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class EnableFormService {

    /**
     * Habilita formulario de LogIn.
     *
     * @return 
     */
    public Map<String, String> service() {
        return NORMAL_USERS;        
    }
}
