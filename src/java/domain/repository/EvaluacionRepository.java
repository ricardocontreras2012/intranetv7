/*
 * @(#)EvaluacionPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Curso;
import domain.model.CursoId;
import domain.model.Evaluacion;
import java.math.BigDecimal;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface EvaluacionRepository extends CrudGenericDAO<Evaluacion, Long> {

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    List<Evaluacion> find(Curso curso);

    /**
     * Method description
     *
     * @param evaluacion
     */
    void setStatusConNota(Evaluacion evaluacion);

    /**
     * Method description
     *
     * @param cursoId
     * @param type
     * @param eval
     * @param porc
     */
    void insertEvaluacion(CursoId cursoId, Integer type, Integer eval, Integer porc);

    /**
     * Method description
     *
     * @param cursoId
     * @param type
     * @param correl
     */
    void deleteEvaluacion(CursoId cursoId, Integer type, Integer correl);

    /**
     * Method description
     *
     * @param cursoId
     * @param type
     * @param eval
     * @param pond
     */
    void updatePonderacion(CursoId cursoId, Integer type, Integer eval, BigDecimal pond);
}
