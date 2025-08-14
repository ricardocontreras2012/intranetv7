/*
 * @(#)EgresadoLaboralesDeleteLaboralService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.misdatos.egresado;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.EgresadoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 *
 * @author Alvaro Romero C.
 */
public class EgresadoLaboralesDeleteLaboralService {

    /**
     * Method description
     *
     *
     * @param action
     * @param genericSession
     * @param es
     * @param pos
     * @param key
     *
     * @return
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, EgresadoSession es, Integer pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        String user = ActionUtil.getDBUser();

        beginTransaction(user);
        ContextUtil.getDAO().getFichaLaboralRepository(user).deleteLaboral(
                es.getFichaLaboralList().get(pos).getFlabCorrelFicha());
        commitTransaction();
        action.addActionMessage(action.getText("message.datos.borrados"));

        return SUCCESS;
    }
}
