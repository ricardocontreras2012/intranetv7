/*
 * @(#)CommonReporteGetResumenService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonReporteGetResumenService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param agno
     * @param sem
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Integer agno, Integer sem, Integer pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        MiCarreraSupport miCarreraSupport = ws.getMiCarreraSupportList().get(pos);

        ws.setAgnoAct(agno);
        ws.setSemAct(sem);
        ws.setNombreCarrera(miCarreraSupport.getNombreCarrera());

        Integer tipoCarrera = miCarreraSupport.getTcrCtip();
        Integer especialidad = miCarreraSupport.getEspCod();
        String regimen = miCarreraSupport.getRegimen();

        ws.setResumenCurso(ContextUtil.getDAO().getCursoRepository(ActionUtil.getDBUser()).findResumenReportes(agno, sem, tipoCarrera, especialidad, regimen));

        return SUCCESS;
    }
}
