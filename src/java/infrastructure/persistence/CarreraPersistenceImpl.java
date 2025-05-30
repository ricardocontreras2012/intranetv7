/*
 * @(#)CarreraPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.CarreraPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Carrera;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S and Javier Frez V.
 * @version 2.1, 18/12/2023
 */
public final class CarreraPersistenceImpl extends CrudAbstractDAO<Carrera, Long> implements CarreraPersistence {
    /**
     * Method description
     *
     * @param cod
     * @return
     */
    @Override
    public Carrera find(Integer cod) {
        Criteria criteria = getSession().createCriteria(Carrera.class);
        criteria.add(eq("carCod", cod));

        return (Carrera) criteria.uniqueResult();
    }
}
