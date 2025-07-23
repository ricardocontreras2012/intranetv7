/*
 * @(#)MencionInfoIntranetPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.MencionInfoIntranet;
import domain.model.Plan;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface MencionInfoIntranetRepository extends CrudGenericDAO<MencionInfoIntranet, Long> {

    /**
     * Method description
     *
     * @param plan
     * @return
     */
    MencionInfoIntranet find(Plan plan);
}
