/*
 * @(#)EstadoCivilPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.EstadoCivil;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface EstadoCivilPersistence extends CrudGenericDAO<EstadoCivil, Long> {

    /**
     * Method description
     *
     * @return
     */
    List<EstadoCivil> find();
}
