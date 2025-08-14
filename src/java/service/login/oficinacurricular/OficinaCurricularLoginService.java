/*
 * @(#)OficinaCurricularLoginService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.login.oficinacurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class OficinaCurricularLoginService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param key
     *
     * @return
     *
     * @throws Exception
     */
    public String service(GenericSession genericSession, String key) throws Exception {
       
        genericSession.getWorkSession(key).setTramites(ContextUtil.getTramiteList());
        return SUCCESS;
    }
}
