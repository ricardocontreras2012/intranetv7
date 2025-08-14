/*
 * @(#)ProfesorSaveNewReporteService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.reporteclase.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import domain.model.CursoId;
import domain.model.ReporteClase;
import domain.model.ReporteClaseId;
import java.io.File;
import static java.lang.Integer.valueOf;
import java.util.Iterator;
import domain.repository.ReporteClaseRepository;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getSysdate;
import static infrastructure.util.DateUtil.stringToDate;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import static infrastructure.util.common.CommonArchivoUtil.doUpload;
import static infrastructure.util.common.CommonArchivoUtil.getMaterialFileName;
import infrastructure.util.common.CommonSequenceUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorSaveNewReporteService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param fecha
     * @param horario
     * @param objetivos
     * @param contenido
     * @param observaciones
     * @param metodo
     * @param upload
     * @param uploadFileName
     * @param recupera
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     * @throws Exception Si el servico genera una exception.
     */
    public String service(ActionCommonSupport action, GenericSession genericSession,
            String fecha, String horario, String objetivos, String contenido,
            String observaciones, String metodo, File upload, String uploadFileName,
            String recupera, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = genericSession.getCurso(key);
        CursoId cursoId = curso.getId();
        ReporteClaseId reporteId = new ReporteClaseId();

        reporteId.setRclaAsign(cursoId.getCurAsign());
        reporteId.setRclaElect(cursoId.getCurElect());
        reporteId.setRclaCoord(cursoId.getCurCoord());
        reporteId.setRclaSecc(cursoId.getCurSecc());
        reporteId.setRclaAgno(cursoId.getCurAgno());
        reporteId.setRclaSem(cursoId.getCurSem());
        reporteId.setRclaComp(cursoId.getCurComp());
        reporteId.setRclaDia(horario.substring(0, 1));
        reporteId.setRclaModulo(valueOf(horario.substring(1, 2)));
        reporteId.setRclaFecClase(stringToDate(fecha));

        ReporteClase reporte = new ReporteClase();

        reporte.setId(reporteId);
        reporte.setRclaContenido(contenido);
        reporte.setRclaMetodo(metodo);
        reporte.setRclaObApren(objetivos);
        reporte.setRclaObs(observaciones);
        reporte.setRclaFecGen(getSysdate());

        if (recupera != null && !recupera.trim().isEmpty()) {
            reporte.setRclaFecRecupera(stringToDate(recupera));
        }

        if (upload != null) {
            Integer folio = CommonSequenceUtil.getDocumentSeq();
            String nombre = getMaterialFileName(uploadFileName, folio);

            doUpload(action, upload, nombre, "mat");
            reporte.setRclaAttach(nombre);
        }

        ReporteClaseRepository reporteRepository
                = ContextUtil.getDAO().getReporteClaseRepository(ActionUtil.getDBUser());
        String retValue = SUCCESS;

        if (reporteRepository.exists(reporte)) {
            ws.setReporte(reporte);
            action.addActionError(action.getText("error.reporte.repetido"));
            retValue = "yaExiste";
        } else {
            beginTransaction(ActionUtil.getDBUser());
            reporteRepository.makePersistent(reporte);

            Iterator<ReporteClase> iter = reporteRepository.find(cursoId).iterator();
            int i = 1;

            while (iter.hasNext()) {
                reporte = iter.next();
                reporte.setRclaSesion(i++);
                reporteRepository.makePersistent(reporte);
            }

            commitTransaction();
            ws.setReporte(null);
            LogUtil.setLogCurso(genericSession.getRut(), curso);
        }

        return retValue;
    }
}
