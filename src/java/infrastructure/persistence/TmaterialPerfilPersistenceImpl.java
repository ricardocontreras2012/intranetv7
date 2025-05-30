/*
 * @(#)TmaterialPerfilPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.TmaterialPerfilPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.TmaterialPerfil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import static org.hibernate.criterion.Order.asc;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class TmaterialPerfilPersistenceImpl extends CrudAbstractDAO<TmaterialPerfil, Long>
        implements TmaterialPerfilPersistence {

    /**
     * Method description
     *
     * @return
     * @throws HibernateException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<TmaterialPerfil> find() throws HibernateException {
        Criteria criteria = getSession().createCriteria(TmaterialPerfil.class);

        criteria.createAlias("tmaterial", "tmaterial");
        criteria.addOrder(asc("tmaterial.tmaDes"));

        return criteria.list();
    }
}
