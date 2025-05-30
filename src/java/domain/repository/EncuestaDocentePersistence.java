/*
 * @(#)EncuestaDocentePersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.EncuestaDocente;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface EncuestaDocentePersistence extends CrudGenericDAO<EncuestaDocente, Long> {

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param carrera
     * @param mencion
     * @param tipo
     * @return
     */
    EncuestaDocente find(Integer agno, Integer sem, Integer carrera, Integer mencion, String tipo);
    EncuestaDocente findAutoEval(Integer agno, Integer sem);
}
