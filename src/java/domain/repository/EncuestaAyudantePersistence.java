/*
 * @(#)EncuestaAyudantePersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.EncuestaAyudante;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface EncuestaAyudantePersistence extends CrudGenericDAO<EncuestaAyudante, Long> {

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
    EncuestaAyudante find(Integer agno, Integer sem, Integer carrera, Integer mencion, String tipo);
}
