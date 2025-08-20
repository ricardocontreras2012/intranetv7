/*
 * @(#)ProfesorSetPorcentajesRelativosService.java
 *
 * Copyright (c) 2023 FAE-USACH
 */
package service.evaluacion.profesor;

import domain.model.Tevaluacion;
import domain.model.Curso;
import domain.model.Evaluacion;
import domain.model.EvaluacionId;
import domain.model.CursoTevaluacionId;
import domain.model.CursoTevaluacion;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 * Servicio encargado de gestionar los porcentajes relativos de las evaluaciones
 * dentro del contexto de un curso. Este servicio verifica si existen evaluaciones
 * previas asociadas a un curso y si no, establece evaluaciones predeterminadas.
 * Además, gestiona la lista de evaluaciones en función de la información disponible.
 * 
 * @version 2.0, 24/05/2012
 * @author Ricardo Contreras S.
 */
public final class ProfesorSetPorcentajesRelativosService {

    /**
     * Método principal del servicio que gestiona las evaluaciones y los porcentajes
     * relativos. Verifica si existen evaluaciones asociadas al curso y, en caso
     * contrario, establece valores predeterminados.
     * 
     * @param genericSession Sesión de trabajo.
     * @param key LLave única que permite acceder a los datos de la sesión.
     * @return Retorna el estado de la acción, en este caso, siempre "SUCCESS".
     */
    public String service(GenericSession genericSession, String key) {

        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = ws.getCurso();
        List<Evaluacion> lEvaluacion
                = ContextUtil.getDAO().getEvaluacionRepository(ActionUtil.getDBUser()).find(curso);

        curso.setEvaluacionList(lEvaluacion);

        // Si existen evaluaciones, se configuran en la sesión
        if (lEvaluacion != null && !lEvaluacion.isEmpty()) {
            ws.setEvaluacionList(lEvaluacion);
            ws.setCursoTevaluacion(ContextUtil.getDAO().getCursoTevaluacionRepository(ActionUtil.getDBUser()).find(curso));
        } else {
            // Si no existen evaluaciones, se configuran las predeterminadas
            lEvaluacion = setDefault(genericSession, curso, key);
        }

        // Se crea una lista vacía de Tevaluacion
        ws.setTevaluacion(new ArrayList<>());

        // Filtra las Tevaluacion que no están asociadas a las Evaluaciones del curso
        final List<Evaluacion> finalEvaluacionList = lEvaluacion;

        ContextUtil.getTevaluacionList().stream()
                .filter(tevaluacion
                        -> finalEvaluacionList.stream()
                        .noneMatch(aLEvaluacion
                                -> aLEvaluacion.getCursoTevaluacion().getTevaluacion().getTevalCod().intValue()
                        == tevaluacion.getTevalCod().intValue()
                        )
                )
                .forEach(tevaluacion -> ws.getTevaluacion().add(tevaluacion));

        // Se registra el log con el curso correspondiente
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        return SUCCESS;
    }

    /**
     * Método que establece evaluaciones predeterminadas en caso de que no existan
     * evaluaciones previas asociadas al curso. Crea las evaluaciones basadas en los
     * requisitos de evaluación de cada Tevaluacion.
     *
     * @param genericSession Sesión de trabajo.
     * @param curso Objeto Curso que contiene la información del curso actual.
     * @param key LLave única para acceder a los datos de la sesión.
     * @return Lista de Evaluacion con las evaluaciones predeterminadas creadas.
     */
    private List<Evaluacion> setDefault(GenericSession genericSession, Curso curso, String key) {
        List<Evaluacion> lEvaluacion = new ArrayList<>();
        List<CursoTevaluacion> cursoListTevaluacion = new ArrayList<>();

        // Itera sobre la lista de Tevaluacion para crear las evaluaciones predeterminadas
        for (int i = 0; i < ContextUtil.getTevaluacionList().size(); i++) {
            Tevaluacion tevaluacion = ContextUtil.getTevaluacionList().get(i);

            // Solo se procesan las Tevaluaciones que requieren un número mínimo de evaluaciones
            if (tevaluacion.getTevalMinRequeridas() > 0) {
                CursoTevaluacion cursoTevaluacion = new CursoTevaluacion();
                CursoTevaluacionId cursoTevaluacionId = new CursoTevaluacionId(curso.getId(), tevaluacion.getTevalCod());

                cursoTevaluacion.setId(cursoTevaluacionId);
                cursoTevaluacion.setCtevaNumEval(tevaluacion.getTevalMinRequeridas());
                cursoTevaluacion.setCtevaPorc(0);  // Porcentaje inicial
                cursoTevaluacion.setCurso(curso);
                cursoTevaluacion.setTevaluacion(tevaluacion);
                cursoListTevaluacion.add(cursoTevaluacion);

                Integer numEvals = tevaluacion.getTevalMinRequeridas();
                BigDecimal porc = new BigDecimal(100).divide(new BigDecimal(numEvals)).setScale(3, RoundingMode.FLOOR);

                // Crea las evaluaciones basadas en el número mínimo de evaluaciones requerido
                for (int n = 0; n < numEvals; n++) {
                    Evaluacion evaluacion = new Evaluacion();
                    EvaluacionId evaluacionId = new EvaluacionId(cursoTevaluacionId, n + 1);

                    evaluacion.setId(evaluacionId);
                    evaluacion.setCursoTevaluacion(cursoTevaluacion);

                    // Asigna el porcentaje a cada evaluación
                    if (n < numEvals - 1) {
                        evaluacion.setEvalPorc(porc);
                    } else {
                        evaluacion.setEvalPorc(new BigDecimal(100).subtract(porc.multiply(new BigDecimal(n))));
                    }

                    evaluacion.setEvalStatus("L");  // Establece el estado de la evaluación como "Bloqueada"
                    lEvaluacion.add(evaluacion);
                }
            }
        }

        // Se configura la sesión con las evaluaciones y tevaluaciones creadas
        genericSession.getWorkSession(key).setCursoTevaluacion(cursoListTevaluacion);
        genericSession.getWorkSession(key).setEvaluacionList(lEvaluacion);

        return lEvaluacion;
    }
}
