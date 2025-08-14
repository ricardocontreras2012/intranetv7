/*
 * @(#)AlumnoEncuestaDocenteService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.encuesta.alumno;

import domain.model.CursoProfesor;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.AlumnoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.common.CommonAlumnoUtil;
import infrastructure.util.common.CommonEncuestaUtil;
import java.util.Objects;
import java.util.Optional;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoEncuestaDocenteService {

    public  String searchService(GenericSession genericSession, String key) {
        return CommonAlumnoUtil.searchEncuestaDocente(genericSession, key, "I");
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
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public  String saveService(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        String user = ActionUtil.getDBUser();
        CursoProfesor cursoProfesor = ws.getCursoProfesor();

        if (cursoProfesor.equals(ws.getCursoProfesorList().get(0))) {
            beginTransaction(user);
            int correl = ContextUtil.getDAO().getScalarRepository(user).getSecuenciaEncuesta();
    
            parameters.entrySet().stream()
                    .filter(entry -> entry.getKey().startsWith("P_"))
                    .forEach(entry -> {
                        String field = entry.getKey();
                        String[] values = entry.getValue();
                        int pos = field.lastIndexOf('_');

                        try {
                            int apartado = Integer.parseInt(field.substring(2, pos));
                            int pregunta = Integer.parseInt(field.substring(pos + 1));

                            Optional.ofNullable(values)
                                    .filter(val -> val.length > 0)
                                    .map(val -> val[0])
                                    .map(v -> {
                                        try {
                                            return Integer.parseInt(v); // Intenta convertir a Integer
                                        } catch (NumberFormatException e) {
                                            return null; // Descarta valores no numéricos
                                        }
                                    })
                                    .filter(Objects::nonNull) // Filtra valores nulos
                                    .ifPresent(value -> ContextUtil.getDAO()
                                    .getRespuestaEncuestaDocenteRepository(user)
                                    .doSave(ws.getAluCar(), cursoProfesor, apartado, pregunta, value, correl));
                        } catch (NumberFormatException e) {
                            // Manejo de error en caso de que no se pueda parsear el apartado o pregunta
                            e.printStackTrace();
                        }
                    });

            Integer encuesta = ws.getEncuestaDocente().getEdoNro();
            String comen1 = Optional.ofNullable(parameters.get("comentarioPositivo"))
                    .filter(arr -> arr.length > 0)
                    .map(arr -> arr[0])
                    .orElse(null);

            String comen2 = Optional.ofNullable(parameters.get("comentarioMejora"))
                    .filter(arr -> arr.length > 0)
                    .map(arr -> arr[0])
                    .orElse(null);

            ContextUtil.getDAO()
                    .getComentarioEncuestaDocenteRepository(user)
                    .doUpdate(genericSession.getRut(), cursoProfesor, encuesta, correl + 100000, comen1, comen2);

            commitTransaction();
            ws.getCursoProfesorList().remove(0);
        }

        return SUCCESS;
    }
}