/*
 * @(#)ComunaPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ComunaPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Comuna;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ComunaPersistenceImpl extends CrudAbstractDAO<Comuna, Long> implements ComunaPersistence {

    /**
     * Method description
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Comuna> find() {
        Criteria criteria = getSession().createCriteria(Comuna.class);

        criteria.setFetchMode("region", JOIN);
        criteria.addOrder(asc("comNom"));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Comuna find(Integer comuna) {
        Criteria criteria = getSession().createCriteria(Comuna.class);

        criteria.setFetchMode("region", JOIN);
        criteria.add(eq("comCod", comuna));

        return  (Comuna)criteria.uniqueResult();
    }
}
