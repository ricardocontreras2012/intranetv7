/*
 * @(#)EstadoCivilPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.EstadoCivilPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.EstadoCivil;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class EstadoCivilPersistenceImpl extends CrudAbstractDAO<EstadoCivil, Long> implements EstadoCivilPersistence {

    /**
     * Method description
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EstadoCivil> find() {
        Criteria criteria = getSession().createCriteria(EstadoCivil.class);

        criteria.addOrder(asc("ecivCod"));

        return criteria.list();
    }
}
