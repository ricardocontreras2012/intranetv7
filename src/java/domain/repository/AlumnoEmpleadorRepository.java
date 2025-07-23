/*
 * @(#)AlumnoEmpleadorRepository.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AlumnoEmpleador;
import java.util.List;

/**
 *
 * @author Alvaro Romero C.
 */
public interface AlumnoEmpleadorRepository extends CrudGenericDAO<AlumnoEmpleador, Long> {

    /**
     * Method description
     *
     *
     * @param rut
     *
     * @return
     */
    List<AlumnoEmpleador> find(Integer rut);

    /**
     * Method description
     *
     *
     * @param rut
     * @param correl
     *
     * @return
     */
    AlumnoEmpleador find(Integer rut, Integer correl);

    /**
     * Method description
     *
     *
     * @param correl
     * @param rutAlumno
     * @param rutEmpleador
     * @param actividadEconomica
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     */
    void updateLaboral(Integer correl, Integer rutAlumno, Integer rutEmpleador, Integer actividadEconomica,
            Integer desdeAgno, Integer desdeMes, Integer hastaAgno, Integer hastaMes);

    /**
     * Method description
     *
     *
     * @param correl
     * @param rutAlumno
     * @param rutEmpleador
     * @param indepActividadEconomica
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     */
    void createLaboral(Integer correl, Integer rutAlumno, Integer rutEmpleador, Integer indepActividadEconomica,
            Integer desdeAgno, Integer desdeMes, Integer hastaAgno, Integer hastaMes);

    /**
     * Method description
     *
     *
     * @param correl
     * @param alumno
     */
    void deleteLaboral(Integer correl, Integer alumno);
}
