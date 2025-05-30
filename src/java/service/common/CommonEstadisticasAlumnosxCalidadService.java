/*
 * @(#)CommonEstadisticasAlumnosxCalidadService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.Ccalidad;
import java.util.List;
import java.util.Map;
import static org.apache.struts2.ServletActionContext.getServletContext;
import domain.repository.CcalidadPersistence;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getDate;
import static infrastructure.util.SystemParametersUtil.DATE_FULL_FORMAT;
import static infrastructure.util.SystemParametersUtil.UNIVERSITY_LOGO_PATH1;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonEstadisticasAlumnosxCalidadService {

    /**
     * Method description
     *
     * @param report Parametros requeridos para JsperReport
     * @param agno
     * @param calidad
     * @return
     */
    public static List<Ccalidad> service(Map<String, Object> report, Integer agno, Integer calidad){
        CcalidadPersistence ccalidadPersistence
                = ContextUtil.getDAO().getCcalidadPersistence(ActionUtil.getDBUser());
        List<Ccalidad> nomina = ccalidadPersistence.findxCalidad(agno, calidad);

        report.put("fecha", getDate(DATE_FULL_FORMAT));
        report.put("logoUsach",
                getServletContext().getRealPath(UNIVERSITY_LOGO_PATH1));

        return nomina;
    }
}
