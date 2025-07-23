/*
 * @(#)TramitePersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Tramite;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface TramiteRepository extends CrudGenericDAO<Tramite, Long> {

    /**
     * Method description
     *
     * @return
     */
    List<Tramite> find();
}
