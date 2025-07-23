/*
 * @(#)CommonReporteGetReportesCursoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.CursoId;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonCursoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonReporteGetReportesCursoService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        CursoId id = CommonCursoUtil.getParent(ws.getCurso(), ws.getCargaEspejo());

        genericSession.getWorkSession(key).setReportes(ContextUtil.getDAO().getReporteClaseRepository(ActionUtil.getDBUser()).find(id));
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        return SUCCESS;
    }
}
