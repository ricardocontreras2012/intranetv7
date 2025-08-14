/*
 * @(#)ProfesorRemoveReportesService.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package service.reporteclase.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import domain.repository.ReporteClaseRepository;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorRemoveReportesService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        if ("PR".equals(genericSession.getUserType())
                || (genericSession.isAutoridad()
                && ws.getCurso().cursoPropio(genericSession.getUserType(), genericSession.getUserType(),
                        genericSession.getRut(), genericSession.isAutoridad()))) {

            ReporteClaseRepository reporteClaseRepository
                    = ContextUtil.getDAO().getReporteClaseRepository(ActionUtil.getDBUser());

            beginTransaction(ActionUtil.getDBUser());

            // Elimina reportes si existen en los parámetros
            ws.getReportes().stream()
                    .filter(reporte -> parameters.containsKey("ck_" + ws.getReportes().indexOf(reporte)))
                    .forEach(reporteClaseRepository::makeTransient);

            // Obtiene los reportes y asigna el número de sesión de manera secuencial
            AtomicInteger counter = new AtomicInteger(1);
            reporteClaseRepository.find(genericSession.getCurso(key).getId())
                    .forEach(reporte -> {
                        reporte.setRclaSesion(counter.getAndIncrement());
                        reporteClaseRepository.makePersistent(reporte);
                    });

            commitTransaction();
            LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());
        }

        return SUCCESS;
    }
}
