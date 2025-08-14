/*
 * @(#)OficinaCurricularActaPrintGetActasService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.acta.oficinacurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import domain.repository.ActaCalificacionRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class OficinaCurricularActaPrintGetActasService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        ActaCalificacionRepository actaRepository
                = ContextUtil.getDAO().getActaCalificacionRepository(ActionUtil.getDBUser());

        ws.setActas(actaRepository.findActasxImprimir(ws.getAgnoAct(), ws.getSemAct()));

        return SUCCESS;
    }
}
