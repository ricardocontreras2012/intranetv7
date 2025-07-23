/*
 * @(#)ProfesorAutoEvaluacionService.java
 *
 * Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.CursoProfesor;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonProfesorUtil;

/**
 * Servicio que maneja la lógica de la autoevaluación de los profesores, permitiendo
 * la consulta, guardado y eliminación de respuestas, así como la configuración de
 * las encuestas asociadas.
 * 
 * @version 7, 24/05/2012
 * @author Ricardo Contreras S.
 */
public final class ProfesorAutoEvaluacionService {

    /**
     * Realiza la consulta de las autoevaluaciones del profesor en base a su sesión.
     * 
     * @param genericSession Sesión de trabajo.
     * @param key Llave de la sesión.
     * @return Resultado de la acción.
     */
    public String searchAction(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        return CommonProfesorUtil.getAutoevaluacion(ws, genericSession.getRut());
    }

    /**
     * Muestra el formulario de autoevaluación basado en los cursos disponibles
     * para el profesor.
     * 
     * @param genericSession Sesión de trabajo.
     * @param key Llave de la sesión.
     * @return Resultado de la acción.
     */
    public String showFormService(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        
        if (ws.getCursosAutoEvaluacion().isEmpty()) {
            return "stack"; // Si no hay cursos de autoevaluación, se retorna un error de stack
        }

        // Selección del primer curso de la lista de autoevaluación
        CursoProfesor cpro = ws.getCursosAutoEvaluacion().get(0);
        Integer agno = cpro.getId().getCproAgno();
        Integer sem = cpro.getId().getCproSem();

        ws.setCursoProfesor(cpro);
        ws.setEncuestaDocente(ContextUtil.getDAO().getEncuestaDocenteRepository(ActionUtil.getDBUser()).findAutoEval(agno, sem));
        ws.setPreguntasAutoevaluacionList(ContextUtil.getDAO().getPreguntaAutoEvaluacionRepository(ActionUtil.getDBUser()).find(agno, sem));

        LogUtil.setLogCurso(genericSession.getRut(), cpro.getCurso());
        return SUCCESS;
    }

    /**
     * Guarda las respuestas de la autoevaluación del profesor, incluyendo comentarios
     * adicionales sobre aspectos positivos y de mejora.
     * 
     * @param genericSession Sesión de trabajo.
     * @param parameters Parámetros del formulario con las respuestas.
     * @param key Llave para acceder a los datos de la sesión.
     * @return Resultado de la acción.
     */
    public String saveService(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        CursoProfesor cursoProfesor = ws.getCursoProfesor();
        String user = ActionUtil.getDBUser();

        beginTransaction(user);
        
        // Obtener correlativo para las respuestas
        int correl = ContextUtil.getDAO().getScalarRepository(user).getSecuenciaEncuesta();

        // Guardar respuestas a las preguntas de la autoevaluación
        parameters.entrySet().stream()
            .filter(entry -> entry.getKey().startsWith("P_")) // Filtrar solo las respuestas de preguntas
            .forEach(entry -> {
                String[] tmp = entry.getValue();
                int pregunta = parseInt(entry.getKey().substring(entry.getKey().lastIndexOf('_') + 1));
                if (tmp != null) {
                    ContextUtil.getDAO().getRespuestaAutoEvaluacionAcademicoRepository(user).doSave(cursoProfesor, pregunta, valueOf(tmp[0]), correl);
                }
            });
        
        String comen1 = parameters.get("comentarioPositivo") != null ? parameters.get("comentarioPositivo")[0] : null;        
        String comen2 = parameters.get("comentarioMejora") != null ? parameters.get("comentarioMejora")[0] : null;

        ContextUtil.getDAO().getComentarioEncuestaDocenteRepository(user).doUpdate(
            genericSession.getRut(), cursoProfesor, ws.getEncuestaDocente().getEdoNro(), correl + 100000, comen1, comen2);

        commitTransaction();

        // Eliminar el primer curso de la lista de autoevaluación
        ws.getCursosAutoEvaluacion().remove(0);

        return SUCCESS;
    }

    /**
     * Elimina todas las respuestas de la autoevaluación asociadas al profesor.
     * 
     * @param genericSession Sesión de trabajo.
     * @return Resultado de la acción.
     */
    public String removeService(GenericSession genericSession) {
        beginTransaction(ActionUtil.getDBUser());
        
        // Actauliza a null el rut en respuestas de la autoevaluación para el profesor
        ContextUtil.getDAO().getRespuestaAutoEvaluacionAcademicoRepository(ActionUtil.getDBUser()).remove(genericSession.getRut());

        commitTransaction();
        return SUCCESS;
    }
}
