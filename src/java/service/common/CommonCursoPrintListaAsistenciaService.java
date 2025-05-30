/*
 * @(#)CommonListaCursoPrintListaService.java
 *
 * Copyright (c) 2019 FAE-USACH
 */
package service.common;

import domain.model.AluCar;
import domain.model.Curso;
import java.util.List;
import java.util.Map;
import static org.apache.struts2.ServletActionContext.getServletContext;
import session.GenericSession;
import session.WorkSession;
import static infrastructure.util.DateUtil.getDate;
import infrastructure.util.LogUtil;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.SystemParametersUtil.DATE_FULL_FORMAT;
import static infrastructure.util.common.CommonFacultadUtil.getNombrexAsign;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonCursoPrintListaAsistenciaService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param report Parametros requeridos para JsperReport
     * @param key LLave para aceder a los datos de la sesion.
     * @return
     */
    public static List<AluCar> service(GenericSession genericSession, Map<String, Object> report,
            String key){
        
        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = ws.getCurso();

        report.put("facultad", getNombrexAsign(curso.getId().getCurAsign()));
        report.put("nombreCurso", curso.getNombreFull());
        report.put("profesor", curso.getCurProfesores());
        report.put("horario", curso.getCurHorario());
        report.put("salas", curso.getCurSalas());
        report.put("fecha", getDate(DATE_FULL_FORMAT));
        report.put("logoUsach",
                getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_LOGO_PATH1));


        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        return curso.getNominaAlumnos();
    }
}