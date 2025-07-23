/*
 * @(#)RegionPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Region;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface RegionRepository extends CrudGenericDAO<Region, Long> {
    Region find(Integer region);
}
