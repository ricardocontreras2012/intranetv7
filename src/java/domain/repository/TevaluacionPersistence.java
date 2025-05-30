/*
 * @(#)TevaluacionPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.CursoId;
import domain.model.Tevaluacion;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface TevaluacionPersistence extends CrudGenericDAO<Tevaluacion, Long> {

    /**
     * Method description
     *
     * @param cursoId
     */
    void generaPlanilla(CursoId cursoId);
}
