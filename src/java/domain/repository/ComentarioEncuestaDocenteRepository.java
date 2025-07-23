/*
 * @(#)ComentarioEncuestaDocenteRepository.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ComentarioEncuestaDocente;
import domain.model.Curso;
import domain.model.CursoProfesor;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ComentarioEncuestaDocenteRepository extends CrudGenericDAO<ComentarioEncuestaDocente, Long> {

    /**
     * Method description
     *
     * @param cursoId
     * @param rutProf
     * @param tipo
     * @return
     */
    List<ComentarioEncuestaDocente> find(Curso cursoId, Integer rutProf, String tipo);
    void doUpdate(Integer rutAlu, CursoProfesor cursoProfesor, Integer encuesta, Integer correl, String comen1, String comen2);
}
