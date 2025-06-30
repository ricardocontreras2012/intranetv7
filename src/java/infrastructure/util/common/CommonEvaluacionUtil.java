/*
 * @(#)CommonEvaluacionUtil.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.util.common;

import domain.model.Curso;
import domain.model.CursoTevaluacion;
import domain.model.Evaluacion;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import domain.repository.CursoTevaluacionPersistence;
import domain.repository.EvaluacionPersistence;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonEvaluacionUtil {

    /**
     * Method description
     *
     *
     * @param curso
     * @param modo
     */
    public static void setModoEvaluacion(Curso curso, String modo) {
        beginTransaction(ActionUtil.getDBUser());
        curso.setModoEvaluacion(modo);
        commitTransaction();
    }

    /**
     * Method description
     *
     *
     * @param parameters
     * @param tiposEvaluaciones
     * @param cursoTevaluacionPersistence
     * @param curso
     */
    public static void resoloverDiferenciasTipos(Map<String, String[]> parameters,
            List<CursoTevaluacion> tiposEvaluaciones, CursoTevaluacionPersistence cursoTevaluacionPersistence,
            Curso curso) {
        resolverTiposEliminados(parameters, tiposEvaluaciones, cursoTevaluacionPersistence, curso);
        resolverTiposNuevos(parameters, tiposEvaluaciones, cursoTevaluacionPersistence, curso);
    }

    /**
     * Method description
     *
     *
     * @param parameters
     * @param tiposEvaluaciones
     * @param cursoTevaluacionPersistence
     * @param curso
     */
    public static void resolverTiposEliminados(Map<String, String[]> parameters,
            List<CursoTevaluacion> tiposEvaluaciones,
            CursoTevaluacionPersistence cursoTevaluacionPersistence,
            Curso curso) {
        tiposEvaluaciones.stream()
                .filter(tiposEvaluacion -> Optional.ofNullable(parameters.get("porc_" + tiposEvaluacion.getId().getCtevaTeva() + "_1"))
                .map(arr -> arr.length == 0) // Verifica si el parámetro está vacío
                .orElse(true)) // Si el parámetro es null o vacío, lo consideramos para eliminar
                .forEach(tiposEvaluacion -> cursoTevaluacionPersistence.delete(curso.getId(), tiposEvaluacion.getId().getCtevaTeva()));  // Elimina
    }

    /**
     * Method description
     *
     *
     * @param parameters
     * @param tiposEvaluaciones
     * @param cursoTevaluacionPersistence
     * @param curso
     */
    public static void resolverTiposNuevos(Map<String, String[]> parameters, List<CursoTevaluacion> tiposEvaluaciones,
            CursoTevaluacionPersistence cursoTevaluacionPersistence, Curso curso) {
        ContextUtil.getTevaluacionList().stream()
                .filter(tipoEvaluacion -> Optional.ofNullable(parameters.get("porc_" + tipoEvaluacion.getTevalCod() + "_1"))
                .map(arr -> arr.length > 0) // Verifica que el parámetro exista y no esté vacío
                .orElse(false))
                .filter(tipoEvaluacion -> tiposEvaluaciones.stream()
                .noneMatch(te -> te.getId().getCtevaTeva().intValue() == tipoEvaluacion.getTevalCod().intValue())) // Verifica si el tipo ya existe en tiposEvaluaciones
                .forEach(tipoEvaluacion -> cursoTevaluacionPersistence.insert(curso.getId(), tipoEvaluacion.getTevalCod())); // Si no existe, inserta

    }

    /**
     * Method description
     *
     *
     * @param parameters
     * @param evaluaciones
     * @param evaluacionPersistence
     * @param curso
     */
    public static void resolverDiferenciasEvaluaciones(Map<String, String[]> parameters, List<Evaluacion> evaluaciones,
            EvaluacionPersistence evaluacionPersistence, Curso curso) {
        resolverEvaluacionesEliminadas(parameters, evaluaciones, evaluacionPersistence, curso);
        resolverEvaluacionesNuevas(parameters, evaluaciones, evaluacionPersistence, curso);
    }

    /**
     * Method description
     *
     *
     * @param parameters
     * @param evaluaciones
     * @param evaluacionPersistence
     * @param curso
     */
    public static void resolverEvaluacionesEliminadas(Map<String, String[]> parameters, List<Evaluacion> evaluaciones,
            EvaluacionPersistence evaluacionPersistence, Curso curso) {
        //beginTransaction(ActionUtil.getDBUser());

        evaluaciones.stream()
                .filter(evaluacion -> Optional.ofNullable(parameters.get("porc_" + evaluacion.getId().getEvalTeva() + "_"
                + evaluacion.getId().getEvalEval()))
                .map(arr -> arr.length == 0) // Comprobamos si el arreglo está vacío
                .orElse(true)) // Si no hay arreglo, se considera vacío
                .forEach(evaluacion -> evaluacionPersistence.deleteEvaluacion(curso.getId(), evaluacion.getId().getEvalTeva(),
                evaluacion.getId().getEvalEval()));

        //commitTransaction();
    }

    /**
     * Method description
     *
     *
     * @param parameters
     * @param evaluaciones
     * @param evaluacionPersistence
     * @param curso
     */
    public static void resolverEvaluacionesNuevas(Map<String, String[]> parameters, List<Evaluacion> evaluaciones,
            EvaluacionPersistence evaluacionPersistence, Curso curso) {
        //beginTransaction(ActionUtil.getDBUser());

        ContextUtil.getTevaluacionList().forEach(tipoEvaluacion -> {
            Map<String, String[]> mapEvaluaciones = getMapEvaluaciones(parameters, tipoEvaluacion.getTevalCod());
            int numEvaluaciones = getNumEvaluaciones(evaluaciones, tipoEvaluacion.getTevalCod());

            mapEvaluaciones.entrySet().stream()
                    .map(entry -> getEvaluacion(entry.getKey()))
                    .filter(evalMap -> evalMap > numEvaluaciones)
                    .forEach(evalMap -> evaluacionPersistence.insertEvaluacion(curso.getId(), tipoEvaluacion.getTevalCod(), evalMap, 0));
        });

        //commitTransaction();
    }

    /**
     * Method description
     *
     *
     * @param parameters
     * @param tipo
     *
     * @return
     */
    private static Map<String, String[]> getMapEvaluaciones(Map<String, String[]> parameters, Integer tipo) {
        return parameters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("porc_" + tipo + "_")) // Filtra las entradas por el prefijo
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));  // Recoge las entradas filtradas en un nuevo Map
    }

    /**
     * Method description
     *
     *
     * @param porc
     *
     * @return
     */
    private static int getEvaluacion(String porc) {
        int posIni = porc.indexOf('_', 5) + 1;

        return parseInt(porc.substring(posIni));
    }

    /**
     * Method description
     *
     *
     * @param pos
     * @param porc
     *
     * @return
     */
    private static int getTipo(int pos, String porc) {
        return parseInt(porc.substring(pos, porc.indexOf('_', pos)));
    }

    /**
     * Method description
     *
     *
     * @param evaluaciones
     * @param tipo
     *
     * @return
     */
    private static int getNumEvaluaciones(List<Evaluacion> evaluaciones, int tipo) {
        return (int) evaluaciones.stream()
                .filter(evaluacion -> evaluacion.getId().getEvalTeva() == tipo) // Filtra las evaluaciones por tipo
                .count();  // Cuenta cuántas evaluaciones cumplen la condición
    }

    /**
     * Method description
     *
     *
     * @param parameters
     * @param evaluacionPersistence
     * @param curso
     */
    public static void setearPorcentajesEvaluaciones(Map<String, String[]> parameters,
            EvaluacionPersistence evaluacionPersistence, Curso curso) {
        String prefijo = "porc_";

        beginTransaction(ActionUtil.getDBUser());

        parameters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(prefijo)) // Filtra las entradas que comienzan con el prefijo
                .forEach(entry -> {
                    String porc = entry.getKey();
                    String[] valPorc = entry.getValue();
                    int tipo = getTipo(prefijo.length(), porc);
                    int eval = getEvaluacion(porc);

                    // Actualiza la ponderación
                    evaluacionPersistence.updatePonderacion(
                            curso.getId(),
                            tipo,
                            eval,
                            new BigDecimal(valPorc[0].replace(",", "."))
                    );
                });

        commitTransaction();
    }

    /**
     * Method description
     *
     *
     * @param parameters
     * @param cursoTevaluacionPersistence
     * @param curso
     */
    public static void setearPorcentajesTipos(Map<String, String[]> parameters,
            CursoTevaluacionPersistence cursoTevaluacionPersistence, Curso curso) {
        String prefijo = "porcTbody_";

        beginTransaction(ActionUtil.getDBUser());

        parameters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(prefijo)) // Filtra las claves que comienzan con el prefijo
                .forEach(entry -> {
                    String porc = entry.getKey();
                    String[] val = entry.getValue();

                    cursoTevaluacionPersistence.updatePonderacion(
                            curso.getId(),
                            parseInt(porc.substring(prefijo.length())), // Extrae el tipo desde la clave
                            new BigDecimal(val[0]) // Convierte el valor a BigDecimal
                    );
                });

        commitTransaction();
    }

}
