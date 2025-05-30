/*
 * @(#)AsistenciaAlumnoNominaPersistence.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCarId;
import domain.model.AsistenciaAlumnoNomina;
import domain.model.Curso;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface AsistenciaAlumnoNominaPersistence extends CrudGenericDAO<AsistenciaAlumnoNomina, Long> {

    /**
     * Method description
     *
     * @param correl
     * @return
     */
    List<AsistenciaAlumnoNomina> find(Integer correl);

    /**
     * Method description
     *
     * @param correl
     */
    void delete(Integer correl);

    /**
     * Method description
     *
     * @param aluCarId
     * @param correl
     * @param asistio
     */
    void updateNomina(AluCarId aluCarId, Integer correl, Integer asistio);

    /**
     * Method description
     *
     * @param curso
     * @param rut
     * @return
     */
    List<AsistenciaAlumnoNomina> find(Curso curso, Integer rut);

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    List<AsistenciaAlumnoNomina> find(Curso curso);
}
