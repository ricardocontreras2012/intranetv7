/*
 * @(#)SecretariaDocenteLoginService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.secretariadocente;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Administrativo;
import java.util.Map;
import session.GenericSession;
import session.SecretariaSession;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class SecretariaDocenteLoginService {

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
    public static String service(Map<String, Object> session) throws Exception {       
        SecretariaSession secreSession = new SecretariaSession();
        GenericSession genericSession = (GenericSession)session.get("genericSession");
        Administrativo administrativo = genericSession.getAdministrativo();
        secreSession.setAdministrativo(administrativo);
        session.put("secretariaSession", secreSession);
        LogUtil.setLog(genericSession.getRut());
        return SUCCESS;
    }
}
