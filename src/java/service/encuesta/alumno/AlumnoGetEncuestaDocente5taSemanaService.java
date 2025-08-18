/*
 * @(#)AlumnoGetEncuestaDocente5taSemanaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.encuesta.alumno;

import domain.model.CursoProfesor;
import static com.opensymphony.xwork2.Action.SUCCESS;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;
import java.util.Map;
import session.AlumnoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.isNotNull;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.common.CommonAlumnoUtil;
import infrastructure.util.common.CommonEncuestaUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoGetEncuestaDocente5taSemanaService {

    public  String search(GenericSession genericSession, String key) {
        return CommonAlumnoUtil.searchEncuestaDocente(genericSession, key, "V");
    }

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param alumnoSession
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public  String showFormService(GenericSession genericSession, AlumnoSession alumnoSession, String key) {
        return CommonEncuestaUtil.showFormService(genericSession, alumnoSession, key);
    }

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param alumnoSession
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public  String saveService(GenericSession genericSession, Map<String, String[]> parameters, AlumnoSession alumnoSession, String key) {
        Integer correl;

        WorkSession ws = genericSession.getWorkSession(key);
        CursoProfesor cursoProfesor = ws.getCursoProfesor();
        alumnoSession.setBienvenida(false);
        if (cursoProfesor == ws.getCursoProfesorList().get(0)) {            
            correl = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getSecuenciaEncuesta();
            beginTransaction(ActionUtil.getDBUser());

            parameters.entrySet().stream()
                    .filter(entry -> entry.getKey().startsWith("P_"))
                    .forEach(entry -> {
                        String field = entry.getKey();
                        String[] tmp = entry.getValue();
                        int pos = field.lastIndexOf('_');
                        int pregunta = parseInt(field.substring(pos + 1));

                        if (tmp != null) {
                            ContextUtil.getDAO()
                                    .getRespuestaEncuestaDocenteRepository(ActionUtil.getDBUser())
                                    .doSaveEncuestaIntermedia(ws.getAluCar(), cursoProfesor, pregunta, valueOf(tmp[0]), correl);
                        }
                    });

            String[] tmpPositivo = parameters.get("comentarioPositivo");
            String[] tmpMejora = parameters.get("comentarioMejora");

            if (isNotNull(tmpPositivo) || isNotNull(tmpMejora)) {
                Integer encuesta = ws.getEncuestaDocente().getEdoNro();
                String comen1 = null;
                String comen2 = null;

                if (tmpPositivo != null) {
                    comen1 = tmpPositivo[0];
                }

                if (tmpMejora != null) {
                    comen2 = tmpMejora[0];
                }

                ContextUtil.getDAO().getComentarioEncuestaDocenteRepository(ActionUtil.getDBUser()).doUpdate(genericSession.getRut(), cursoProfesor, encuesta, correl + 100000, comen1, comen2);
            }

            commitTransaction();
            ws.getCursoProfesorList().remove(0);
        }

        return SUCCESS;
    }
}
