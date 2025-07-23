/*
 * @(#)ProfesorReporteSaveModifiedReporteService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.ReporteClase;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getSysdate;
import static infrastructure.util.DateUtil.stringToDate;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorReporteSaveModifiedReporteService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param sesionReporte
     * @param objetivos
     * @param contenido
     * @param observaciones
     * @param metodo
     * @param recuperacion
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     * @throws Exception Si el servico genera una exception.
     */
    public String service(GenericSession genericSession, Integer sesionReporte, String objetivos,
            String contenido, String observaciones, String metodo, String recuperacion, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        ReporteClase reporte = ws.getReportes().get(sesionReporte);

        reporte.setRclaContenido(contenido);
        reporte.setRclaMetodo(metodo);
        reporte.setRclaObApren(objetivos);
        reporte.setRclaObs(observaciones);

        if (recuperacion != null && !recuperacion.trim().isEmpty()) {
            reporte.setRclaFecRecupera(stringToDate(recuperacion));
        }

        reporte.setRclaFecGen(getSysdate());
        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getReporteClaseRepository(ActionUtil.getDBUser()).makePersistent(reporte);
        commitTransaction();
        ws.setReporte(null);
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        return SUCCESS;
    }
}
