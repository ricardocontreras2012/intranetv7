/*
 * @(#)ProfesorEvaluacionPorcentajesAbsolutosService.java
 *
 * Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

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
import java.util.stream.IntStream;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 * Servicio encargado de gestionar las evaluaciones y sus porcentajes absolutos en el contexto de un curso.
 * Este servicio maneja la carga y configuración de las evaluaciones para un curso, con la capacidad
 * de asignar porcentajes predeterminados en caso de que no existan evaluaciones configuradas.
 * 
 * @author Ricardo Contreras S.
 * @version 1.0, 2023-12-18
 */
public final class ProfesorEvaluacionPorcentajesAbsolutosService {

    /**
     * Servicio principal que obtiene las evaluaciones para un curso y las asigna al contexto de sesión.
     * Si no se encuentran evaluaciones, asigna valores predeterminados.
     *
     * @param genericSession Sesión de trabajo que mantiene el estado global de la aplicación.
     * @param key Llave única para acceder a los datos de la sesión.
     * @return Estado de la acción, en este caso siempre retorna "SUCCESS".
     */
    public String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = ws.getCurso();

        // Obtener las evaluaciones asociadas al curso desde la base de datos
        List<Evaluacion> lEvaluacion = ContextUtil.getDAO().getEvaluacionRepository(ActionUtil.getDBUser()).find(curso);

        // Asignar las evaluaciones al curso
        curso.setEvaluacionList(lEvaluacion);

        // Si hay evaluaciones configuradas, se cargan en la sesión
        if (lEvaluacion != null && !lEvaluacion.isEmpty()) {
            ws.setEvaluacionList(lEvaluacion);
            ws.setCursoTevaluacion(ContextUtil.getDAO().getCursoTevaluacionRepository(ActionUtil.getDBUser()).find(curso));
        } else {
            // Si no se encuentran evaluaciones, se asignan valores predeterminados
            setDefault(genericSession, curso, key);
        }

        // Establecer la lista de evaluación
        ws.setTevaluacion(ContextUtil.getTevaluacionList());
        // Registrar la actividad en el log
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        return SUCCESS;
    }

    /**
     * Asigna configuraciones predeterminadas de evaluaciones y porcentajes al curso en caso de que no haya
     * configuraciones previas.
     *
     * @param genericSession Sesión de trabajo que mantiene el estado global de la aplicación.
     * @param curso El curso al que se le asignarán las evaluaciones y sus porcentajes.
     * @param key Llave única para acceder a los datos de la sesión.
     */
    private void setDefault(GenericSession genericSession, Curso curso, String key) {
        List<Evaluacion> lEvaluacion = new ArrayList<>();
        List<CursoTevaluacion> cursoListTevaluacion = new ArrayList<>();       
        
        // Uso de Streams para iterar sobre las evaluaciones disponibles en el contexto global
        ContextUtil.getTevaluacionList().stream()
            .filter(tevaluacion -> tevaluacion.getTevalMinRequeridas() > 0)  // Filtrar las que requieren mínimo de evaluaciones
            .forEach(tevaluacion -> {
                // Crear la configuración de CursoTevaluacion
                CursoTevaluacion cursoTevaluacion = createCursoTevaluacion(curso, tevaluacion);
                cursoListTevaluacion.add(cursoTevaluacion);

                // Calcular el porcentaje
                BigDecimal porc = calculatePorcentaje(tevaluacion.getTevalMinRequeridas());

                // Crear las evaluaciones asociadas con un Stream
                IntStream.range(0, tevaluacion.getTevalMinRequeridas()).forEach(n -> {
                    Evaluacion evaluacion = createEvaluacion(cursoTevaluacion, n, porc);
                    lEvaluacion.add(evaluacion);
                });
            });

        // Asignar las evaluaciones y la configuración del curso a la sesión
        WorkSession ws = genericSession.getWorkSession(key);
        ws.setCursoTevaluacion(cursoListTevaluacion);
        ws.setEvaluacionList(lEvaluacion);
    }

    /**
     * Crea un objeto {@link CursoTevaluacion} a partir de la información de un curso y una evaluación.
     *
     * @param curso El curso al que se le asociará la evaluación.
     * @param tevaluacion La evaluación que se asociará al curso.
     * @return El objeto {@link CursoTevaluacion} configurado.
     */
    private CursoTevaluacion createCursoTevaluacion(Curso curso, Tevaluacion tevaluacion) {
        CursoTevaluacion cursoTevaluacion = new CursoTevaluacion();
        CursoTevaluacionId cursoTevaluacionId = new CursoTevaluacionId(curso.getId(), tevaluacion.getTevalCod());
        cursoTevaluacion.setId(cursoTevaluacionId);
        cursoTevaluacion.setCtevaNumEval(tevaluacion.getTevalMinRequeridas());

        // Usar valor entero para el porcentaje (por ejemplo, 0% como valor inicial)
        cursoTevaluacion.setCtevaPorc(0);  // Cambié el valor de BigDecimal.ZERO a 0 (int)

        cursoTevaluacion.setCurso(curso);
        cursoTevaluacion.setTevaluacion(tevaluacion);
        return cursoTevaluacion;
    }

    /**
     * Calcula el porcentaje de cada evaluación basado en el número de evaluaciones requeridas.
     *
     * @param numEvals El número de evaluaciones requeridas para la evaluación.
     * @return El porcentaje calculado como BigDecimal.
     */
    private BigDecimal calculatePorcentaje(int numEvals) {
        BigDecimal totalPorcentaje = new BigDecimal(100);
        return totalPorcentaje.divide(new BigDecimal(numEvals), 3, RoundingMode.FLOOR);
    }

    /**
     * Crea un objeto {@link Evaluacion} a partir de la configuración de {@link CursoTevaluacion}.
     *
     * @param cursoTevaluacion La configuración de la evaluación del curso.
     * @param n El índice de la evaluación dentro de su conjunto.
     * @param porc El porcentaje asignado a cada evaluación.
     * @return El objeto {@link Evaluacion} configurado.
     */
    private Evaluacion createEvaluacion(CursoTevaluacion cursoTevaluacion, int n, BigDecimal porc) {
        Evaluacion evaluacion = new Evaluacion();
        EvaluacionId evaluacionId = new EvaluacionId(cursoTevaluacion.getId(), n + 1);
        evaluacion.setId(evaluacionId);
        evaluacion.setCursoTevaluacion(cursoTevaluacion);

        // Asignar el porcentaje calculado, ajustando el último porcentaje
        if (n < cursoTevaluacion.getCtevaNumEval() - 1) {
            evaluacion.setEvalPorc(porc);
        } else {
            evaluacion.setEvalPorc(new BigDecimal(100).subtract(porc.multiply(new BigDecimal(n))));
        }

        evaluacion.setEvalStatus("L"); // Estatus de la evaluación: 'L' para "Locked"
        return evaluacion;
    }
}
