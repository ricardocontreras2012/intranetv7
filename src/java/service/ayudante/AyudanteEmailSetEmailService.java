/*
 * @(#)AyudanteEmailSetEmailService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.ayudante;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.emailNormalizado;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AyudanteEmailSetEmailService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param email
     * @return Action status.
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, String email) {
        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getAyudanteRepository(ActionUtil.getDBUser()).setEmail(genericSession.getRut(), emailNormalizado(email));
        commitTransaction();
        genericSession.setEmail(email);
        action.addActionMessage(action.getText("message.datos.grabados"));

        return SUCCESS;
    }
}
