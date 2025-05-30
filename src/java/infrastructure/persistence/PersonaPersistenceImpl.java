/*
 * @(#)PersonaPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.PersonaPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Persona;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class PersonaPersistenceImpl extends CrudAbstractDAO<Persona, Long> implements PersonaPersistence {

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    public Persona find(Integer rut) {
        Criteria criteria = getSession().createCriteria(Persona.class);

        criteria.add(eq("perRut", rut));

        return (Persona) criteria.uniqueResult();
    }
}
