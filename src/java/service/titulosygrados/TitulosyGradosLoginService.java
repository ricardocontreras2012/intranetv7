/*
 * @(#)TitulosyGradosLoginService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.titulosygrados;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.GenericSession;
import session.TitulosyGradosSession;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class TitulosyGradosLoginService {

    /**
     * Method description
     *
     *
     * @param session
     *
     * @return
     *
     * @throws Exception
     */
    public String service(Map<String, Object> session) throws Exception {
        TitulosyGradosSession tygSession = new TitulosyGradosSession();
        GenericSession genericSession = (GenericSession)session.get("genericSession");
        tygSession.setAdministrativo(genericSession.getAdministrativo());
        session.put("titulosyGradosSession", tygSession);
        
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }
}
