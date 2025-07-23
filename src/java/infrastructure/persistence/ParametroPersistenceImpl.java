/*
 * @(#)ParametroPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ParametroRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Parametro;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ParametroPersistenceImpl extends CrudAbstractDAO<Parametro, Long> implements ParametroRepository {

    /**
     * Method description
     *
     * @param param
     * @return
     */
    @Override
    public Parametro find(String param) {
        Criteria criteria = getSession().createCriteria(Parametro.class);

        criteria.add(eq("parNomVar", param));
        return (Parametro) criteria.uniqueResult();

    }
}
