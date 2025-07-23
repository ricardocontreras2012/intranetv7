/*
 * @(#)AsistenciaAlumnoRepository.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AsistenciaAlumno;
import domain.model.Curso;
import java.util.Date;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface AsistenciaAlumnoRepository extends CrudGenericDAO<AsistenciaAlumno, Long> {

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    List<AsistenciaAlumno> find(Curso curso);

    /**
     * Method description
     *
     * @param correl
     */
    void delete(Integer correl);

    /**
     * Method description
     *
     * @param correl
     * @param fecha
     */
    void updateFecha(Integer correl, Date fecha);
}
