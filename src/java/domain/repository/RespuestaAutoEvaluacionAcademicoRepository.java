/*
 * @(#)RespuestaAutoEvaluacionAcademicoPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.CursoProfesor;
import domain.model.RespuestaAutoEvaluacionAcademico;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface RespuestaAutoEvaluacionAcademicoRepository
        extends CrudGenericDAO<RespuestaAutoEvaluacionAcademico, Long> {

    /**
     * Method description
     *
     *
     * @param rut
     *
     * @return
     */
    List<CursoProfesor> getCursos(Integer rut);

    /**
     * Method description
     *
     * @param cursoProfesor
     * @param preg
     * @param resp
     * @param correl
     */
    void doSave(CursoProfesor cursoProfesor, Integer preg, Integer resp, Integer correl);

    void remove(Integer rut);

}
