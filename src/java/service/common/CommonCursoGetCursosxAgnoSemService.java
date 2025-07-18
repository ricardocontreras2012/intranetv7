/*
 * @(#)CommonCursoGetCursosxAgnoSemService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.common.CommonCursoUtil;
import infrastructure.util.common.CommonHorarioUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonCursoGetCursosxAgnoSemService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param agno
     * @param sem
     * @param actionCall
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key, Integer pos, Integer agno, Integer sem, String actionCall) {

        WorkSession ws = genericSession.getWorkSession(key);
        MiCarreraSupport miCarreraSupport = ws.getMiCarreraSupportList().get(pos);
        ws.setAgnoAct(agno);
        ws.setSemAct(sem);
        ws.setModuloHorarioList(CommonHorarioUtil.getModuloHorarioDocencia(agno, sem));
        ws.setNombreCarrera(miCarreraSupport.getNombreCarrera());
        ws.setMiCarreraSupport(miCarreraSupport);
        ws.setActionCall(actionCall);
        CommonCursoUtil.getCursos(genericSession, "*", key); //Cerrados, Transversales y Espejos

        return SUCCESS;
    }
}
