/*
 * @(#)PasswordTicketPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.PasswordTicketRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.PasswordTicket;
import static java.lang.String.valueOf;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class PasswordTicketPersistenceImpl extends CrudAbstractDAO<PasswordTicket, Long>
        implements PasswordTicketRepository {

    /**
     * Method description
     *
     * @param rut
     */
    @Override
    public void deleteTickets(Integer rut) {
        String hql = "delete from password_ticket where pt_rut =" + rut;
        Query query = getSession().createSQLQuery(hql);

        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param passwordTicket
     */
    @Override
    public void insertTicket(PasswordTicket passwordTicket) {
        String hql = "insert into password_ticket (pt_rut, pt_user_type, pt_key, pt_date) VALUES(:rut, :userType, "
                + ":key, SYSDATE)";
        Query query = getSession().createSQLQuery(hql);

        query.setParameter("rut", passwordTicket.getPtRut(), StandardBasicTypes.INTEGER);
        query.setParameter("userType", passwordTicket.getPtUserType(), StandardBasicTypes.STRING);
        query.setParameter("key", passwordTicket.getPtKey(), StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param rut
     * @param key
     * @return
     */
    @Override
    public PasswordTicket find(Integer rut, String key) {
        Criteria criteria = getSession().createCriteria(PasswordTicket.class);

        criteria.add(eq("ptRut", rut));
        criteria.add(eq("ptKey", key));

        return (PasswordTicket) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    public String getKey(Integer rut) {
        String sql = "select get_rand_certificados(:rut) key from dual";
        return (String) getSession().createSQLQuery(sql).setParameter("rut", valueOf(rut), StandardBasicTypes.STRING).uniqueResult();
    }
}
