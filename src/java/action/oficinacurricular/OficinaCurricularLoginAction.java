/*
 * @(#)OficinaCurricularLoginAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.oficinacurricular;

import service.login.oficinacurricular.OficinaCurricularLoginService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class OficinaCurricularLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        return new OficinaCurricularLoginService().service(getGenericSession(), getKey());
    }
}
