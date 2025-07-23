/*
 * @(#)TramitePersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.TramiteRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Tramite;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class TramitePersistenceImpl extends CrudAbstractDAO<Tramite, Long> implements TramiteRepository {

    /**
     * Method description
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Tramite> find() {
        Criteria criteria = getSession().createCriteria(Tramite.class);

        criteria.add(eq("traExcentoPago", 'S'));
        criteria.addOrder(asc("traDescripcion"));

        return criteria.list();
    }
}
