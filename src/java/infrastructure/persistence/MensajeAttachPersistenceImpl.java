/*
 * @(#)MensajeAttachPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.MensajeAttachPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.MensajeAttach;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class MensajeAttachPersistenceImpl extends CrudAbstractDAO<MensajeAttach, Long>
        implements MensajeAttachPersistence {

    /**
     *
     * @param msg
     * @param correl
     * @param file
     */
    @Override
    public void saveAttach(Integer msg, Integer correl, String file)
    {
        String sqlText="insert into mensaje_attach("+
                "mena_correl_msg,mena_correl_attach,mena_attach_file) values("+
                msg+","+correl+",'"+file+"')";
                
        Query query = getSession().createSQLQuery(sqlText);

        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param correl
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MensajeAttach> find(Integer correl) {
        Criteria criteria = getSession().createCriteria(MensajeAttach.class);

        criteria.add(eq("id.menaCorrelMsg", correl));
        criteria.addOrder(asc("id.menaCorrelAttach"));

        return criteria.list();
    }

    /**
     * Method description
     *
     *
     * @param correl
     * @param file
     * @param key
     *
     * @return
     */
    @Override
    public MensajeAttach find(Integer correl, String file, String key) {
        Criteria criteria = getSession().createCriteria(MensajeAttach.class);

        criteria.createAlias("mensaje", "mensaje");
        criteria.add(eq("id.menaCorrelMsg", correl));
        criteria.add(eq("menaAttachFile", file));
        criteria.add(eq("mensaje.msgCorrel", correl));
        criteria.add(eq("mensaje.msgIdSession", key));

        return (MensajeAttach) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     *
     * @param correl
     * @param file
     *
     * @return
     */
    @Override
    public MensajeAttach find(Integer correl, Integer file) {
        Criteria criteria = getSession().createCriteria(MensajeAttach.class);

        criteria.add(eq("id.menaCorrelMsg", correl));
        criteria.add(eq("id.menaCorrelAttach", file));

        return (MensajeAttach) criteria.uniqueResult();
    }
}
