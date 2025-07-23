/*
 * @(#)CursoProfesorRepository.java
 *
 * Copyright (c) Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCarId;
import domain.model.Curso;
import domain.model.CursoProfesor;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface CursoProfesorRepository extends CrudGenericDAO<CursoProfesor, Long> {

    /**
     * Method description
     *
     * @param curso
     * @param rut
     * @return
     */
    boolean exists(Curso curso, Integer rut);

    /**
     * Method description
     *
     *
     * @param aluCarId
     * @param agno
     * @param sem
     *
     * @return
     */
    List<CursoProfesor> getCursosEncuesta(AluCarId aluCarId, Integer agno, Integer sem);
    List<CursoProfesor> getCursosEncuesta(Integer rut);
    List<CursoProfesor> findCursosMallaJefeArea(Integer rut, Integer agno, Integer sem);
    List<CursoProfesor> find(Integer tcarrera, Integer especialidad, String jornada, Integer agno, Integer sem, Integer rut, String perfil, String tipo);
}
