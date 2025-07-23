/*
 * @(#)ExternoPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ExternoRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Externo;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.idEq;
import static org.hibernate.criterion.Restrictions.sqlRestriction;
import org.hibernate.type.StringType;
import org.hibernate.type.StandardBasicTypes;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ExternoPersistenceImpl extends CrudAbstractDAO<Externo, Long> implements ExternoRepository {

    /**
     * Method description
     *
     * @param rut
     * @param password
     * @return
     */
    @Override
    public Externo find(Integer rut, String password) {
        Criteria criteria = getSession().createCriteria(Externo.class);

        criteria.add(idEq(rut));
        criteria.add(sqlRestriction("valid_user_externo(ext_rut, (?))=1", password, new StringType()));

        return (Externo) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    public Externo find(Integer rut) {
        Criteria criteria = getSession().createCriteria(Externo.class);

        criteria.add(idEq(rut));

        return (Externo) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param rut
     * @param password
     */
    @Override
    public void setPassword(Integer rut, String password) {
        String hql = "update Externo set ext_password = get_hash(:rut, :password) where ext_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.setParameter("password", password, StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param rut
     * @param email
     */
    @Override
    public void setMisDatos(Integer rut, String email) {
        String hql = "update Externo set ext_email = :email where ext_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.setParameter("email", email, StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param rut
     */
    @Override
    public void setLastLogin(Integer rut) {
        String hql = "update Externo set ext_last_login = SYSDATE where ext_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Externo> find() {
        Criteria criteria = getSession().createCriteria(Externo.class);

        criteria.addOrder(asc("extPaterno"));
        criteria.addOrder(asc("extMaterno"));
        criteria.addOrder(asc("extNombre"));

        return criteria.list();
    }
}
