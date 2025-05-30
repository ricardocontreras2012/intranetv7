/*
 * @(#)FlagInscripcionPersistenceView.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.FlagInscripcionView;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface FlagInscripcionPersistenceView extends CrudGenericDAO<FlagInscripcionView, Long> {

    /**
     * Method description
     *
     * @param carrera
     * @param mencion
     * @return
     */
    FlagInscripcionView find(Integer carrera, Integer mencion);
}
