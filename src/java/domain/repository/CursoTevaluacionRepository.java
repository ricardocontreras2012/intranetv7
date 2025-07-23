/*
 * @(#)CursoTevaluacionRepository.java
 *
 * Copyright (c) Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Curso;
import domain.model.CursoId;
import domain.model.CursoTevaluacion;
import java.math.BigDecimal;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface CursoTevaluacionRepository extends CrudGenericDAO<CursoTevaluacion, Long> {

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    List<CursoTevaluacion> find(Curso curso);

    /**
     *
     * @param cursoId
     * @param tipo
     */
    void delete(CursoId cursoId, Integer tipo);

    /**
     *
     * @param cursoId
     * @param tipo
     */
    void insert(CursoId cursoId, Integer tipo);

    /**
     *
     * @param cursoId
     * @param type
     * @param pond
     */
    void updatePonderacion(CursoId cursoId, Integer type, BigDecimal pond);
}
