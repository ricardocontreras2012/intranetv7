/*
 * @(#)ProfesorEvaluacionEmitirActaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import domain.repository.ActaCalificacionRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorEvaluacionEmitirActaService {

    /**
     * Method Servicio
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, String key) {

        WorkSession ws = genericSession.getWorkSession(key);
        ActaCalificacionRepository actaRepository
                = ContextUtil.getDAO().getActaCalificacionRepository(ActionUtil.getDBUser());

        beginTransaction(ActionUtil.getDBUser());
        actaRepository.emiteActas(ws.getCurso());
        commitTransaction();
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());
        action.addActionMessage(action.getText("message.acta.emitida"));

        return SUCCESS;
    }
}
