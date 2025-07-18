/*
 * @(#)CommonProfesorCargaHistoricaPrintService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.Curso;
import java.util.List;
import java.util.Map;
import static org.apache.struts2.ServletActionContext.getServletContext;
import session.GenericSession;
import static infrastructure.util.DateUtil.getDate;
import static infrastructure.util.SystemParametersUtil.DATE_FULL_FORMAT;
import static infrastructure.util.SystemParametersUtil.UNIVERSITY_LOGO_PATH1;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonProfesorCargaHistoricaPrintService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param report Parametros requeridos para JsperReport
     * @param key LLave para aceder a los datos de la sesion.
     * @return
     */
    public List<Curso> service(GenericSession genericSession, Map<String, Object> report, String key){
        List<Curso> nomina = genericSession.getWorkSession(key).getProfesor().getCargaHistorica();       
        
        report.put("nombreProfesor", genericSession.getWorkSession(key).getProfesor().getNombre());
        report.put("fecha", getDate(DATE_FULL_FORMAT));
        report.put("logoUsach",
                getServletContext().getRealPath(UNIVERSITY_LOGO_PATH1));

        return nomina;
    }
}
