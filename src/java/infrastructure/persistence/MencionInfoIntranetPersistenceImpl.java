/*
 * @(#)MencionInfoIntranetPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.MencionInfoIntranetRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.MencionInfoIntranet;
import domain.model.Plan;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class MencionInfoIntranetPersistenceImpl extends CrudAbstractDAO<MencionInfoIntranet, Long>
        implements MencionInfoIntranetRepository {

    /**
     * Method description
     *
     * @param plan
     * @return
     */
    @Override
    public MencionInfoIntranet find(Plan plan) {
        Criteria criteria = getSession().createCriteria(MencionInfoIntranet.class);

        criteria.add(eq("id.miniCodCar", plan.getId().getPlaCodCar()));
        criteria.add(eq("id.miniCodMen", plan.getId().getPlaCodMen()));

        return (MencionInfoIntranet) criteria.uniqueResult();
    }
}
