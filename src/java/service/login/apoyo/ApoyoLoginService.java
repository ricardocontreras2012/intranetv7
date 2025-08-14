/*
 * @(#)ApoyoLoginService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.login.apoyo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Administrativo;
import java.util.Map;
import session.GenericSession;
import session.SecretariaSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 07/05/2019
 */
public final class ApoyoLoginService {

    /**
     * Method description
     *
     * @param session
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     * @throws Exception Si el servico genera una exception.
     */
    public String service(Map<String, Object> session, String key) throws Exception {
        SecretariaSession secreSession = new SecretariaSession();
        GenericSession genericSession = (GenericSession)session.get("genericSession");
        Administrativo administrativo = genericSession.getAdministrativo();
        secreSession.setAdministrativo(administrativo);
        session.put("secretariaSession", secreSession);
        return SUCCESS;
    }
}

