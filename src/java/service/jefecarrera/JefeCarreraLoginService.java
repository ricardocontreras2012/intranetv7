/*
 * @(#)JefeCarreraLoginService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.jefecarrera;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class JefeCarreraLoginService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param key
     *
     * @return
     */
    public static String service(GenericSession genericSession, String key) {
        LogUtil.setLog(genericSession.getRut());
        return SUCCESS;
    }
}
