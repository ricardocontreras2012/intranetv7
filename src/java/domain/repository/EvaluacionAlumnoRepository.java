/*
 * @(#)EvaluacionAlumnoPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Curso;
import domain.model.CursoId;
import domain.model.Evaluacion;
import domain.model.EvaluacionAlumno;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface EvaluacionAlumnoRepository extends CrudGenericDAO<EvaluacionAlumno, Long> {

    /**
     * Method description
     *
     * @param evaluacion
     * @return
     */
    List<EvaluacionAlumno> find(Evaluacion evaluacion);

    /**
     * Method description
     *
     * @param rut
     * @param id
     * @return
     */
    List<EvaluacionAlumno> getNotas(Integer rut, CursoId id);

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    List<EvaluacionAlumno> getPlanilla(Curso curso);

    /**
     * Method description
     *
     * @param id
     */
    void sincronizaCurso(CursoId id);
}
