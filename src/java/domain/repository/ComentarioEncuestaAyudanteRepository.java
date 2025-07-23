/*
 * @(#)ComentarioEncuestaAyudanteRepository.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ComentarioEncuestaAyudante;
import domain.model.Curso;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ComentarioEncuestaAyudanteRepository extends CrudGenericDAO<ComentarioEncuestaAyudante, Long> {

    /**
     * Method description
     *
     * @param cursoId
     * @return
     */
    List<ComentarioEncuestaAyudante> find(Curso cursoId);
}
