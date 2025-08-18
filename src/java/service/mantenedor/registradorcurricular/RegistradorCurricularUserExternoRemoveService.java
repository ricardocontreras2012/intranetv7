/*
 * @(#)RegistradorCurricularUserExternoRemoveService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.mantenedor.registradorcurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import domain.repository.ExternoRepository;
import session.GenericSession;
import session.RegistradorSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class RegistradorCurricularUserExternoRemoveService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param registradorSession
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, RegistradorSession registradorSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        ExternoRepository externoRepo
                = ContextUtil.getDAO().getExternoRepository(ActionUtil.getDBUser());

        beginTransaction(ActionUtil.getDBUser());

        for (int i = 0; i < ws.getSentMsgs().size(); i++) {
            if (parameters.get("ck_" + i) != null) {
                externoRepo.makeTransient(
                        registradorSession.getExternoList().get(i));
            }
        }

        commitTransaction();
        registradorSession.setExternoList(externoRepo.find());

        return SUCCESS;
    }
}
