/*
 * @(#)CommonReportePrintResumenService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static java.lang.Boolean.TRUE;
import java.util.List;
import java.util.Map;
import static org.apache.struts2.ServletActionContext.getServletContext;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.CursoResumenSupport;
import static infrastructure.util.DateUtil.getDate;
import static infrastructure.util.SystemParametersUtil.DATE_FULL_FORMAT;
import static infrastructure.util.SystemParametersUtil.UNIVERSITY_LOGO_PATH1;
import static infrastructure.util.common.CommonFacultadUtil.getNombrexAsign;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonReportePrintResumenService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param report Parametros requeridos para JsperReport
     * @param format
     * @param key LLave para aceder a los datos de la sesion.
     * @return
     */
    public List<CursoResumenSupport> service(GenericSession genericSession,
            Map<String, Object> report, String format, String key) {
        WorkSession ws= genericSession.getWorkSession(key);
        List<CursoResumenSupport> nomina = ws.getResumenCurso();

        report.put("facultad", getNombrexAsign(nomina.get(0).getAsign()));
        report.put("nombreCarrera", ws.getNombreCarrera());
        report.put("agnosem",
                ws.getAgnoAct() + "/"
                + ws.getSemAct());
        report.put("fecha", getDate(DATE_FULL_FORMAT));
        report.put("logoUsach",
                getServletContext().getRealPath(UNIVERSITY_LOGO_PATH1));

        if ("XLS".equals(format)) {
            report.put("IS_IGNORE_PAGINATION", TRUE);
        }

        return nomina;
    }
}
