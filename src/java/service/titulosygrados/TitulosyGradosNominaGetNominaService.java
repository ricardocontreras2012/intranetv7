/*
 * @(#)TitulosyGradosNominaGetNominaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.titulosygrados;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class TitulosyGradosNominaGetNominaService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param tipo
     * @param nomina
     * @param agno
     * @param key
     *
     * @return
     *
     * @throws Exception
     */
    public String service(GenericSession genericSession, Integer tipo, String nomina, Integer agno, String key)
            throws Exception {
        genericSession.getWorkSession(key).setExpedienteLogroList(ContextUtil.getDAO().getExpedienteLogroRepository(ActionUtil.getDBUser()).find(nomina, agno, tipo));

        return SUCCESS;
    }
}
