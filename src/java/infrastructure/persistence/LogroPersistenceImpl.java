/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.LogroPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Logro;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 *
 * @author rcontreras
 */
public class LogroPersistenceImpl extends CrudAbstractDAO<Logro, Long>
        implements LogroPersistence {

    @SuppressWarnings("unchecked")
    @Override
    public List<Logro> find()
    {
        Criteria criteria = getSession().createCriteria(Logro.class);

        criteria.addOrder(asc("logrDes"));

        return  criteria.list();
    }

    @Override
    public Logro find(Integer logro)
    {
        Criteria criteria = getSession().createCriteria(Logro.class);

        criteria.createAlias("tlogro", "tlogro");
        criteria.add(eq("logrCod", logro));

        return  (Logro)criteria.uniqueResult();
    }
}
