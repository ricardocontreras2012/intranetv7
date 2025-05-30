/*
 * @(#)RegionPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.RegionPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Region;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class RegionPersistenceImpl extends CrudAbstractDAO<Region, Long> implements RegionPersistence {

    @Override
    public Region find(Integer region) {
        Criteria criteria = getSession().createCriteria(Region.class);

        criteria.add(eq("regCod", region));

        return (Region) criteria.uniqueResult();

    }
}
