/*
 * @(#)DiaPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Dia;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Order.asc;
import domain.repository.DiaRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class DiaPersistenceImpl extends CrudAbstractDAO<Dia, Long> implements DiaRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<Dia> findClases() {
        Criteria criteria = getSession().createCriteria(Dia.class);

        criteria.add(eq("diaClases", "S"));
        criteria.addOrder(asc("diaCorrel"));
        return criteria.list();

    }    
}
