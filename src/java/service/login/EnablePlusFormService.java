/*
 * @(#)EnablePlusFormService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.login;

import java.util.Map;
import static infrastructure.util.AppStaticsUtil.PRIVILEGED_USERS;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class EnablePlusFormService {

    /**
     * Method description
     *
     * @return 
     */
    public Map<String, String> service() {
        return PRIVILEGED_USERS;        
    }
}