/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.ConvalidacionComisionProfPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ConvalidacionComisionProf;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 *
 * @author rcontreras
 */
public class ConvalidacionComisionProfPersistenceImpl extends CrudAbstractDAO<ConvalidacionComisionProf, Long> implements ConvalidacionComisionProfPersistence {

    @SuppressWarnings("unchecked")
    @Override
    public List<ConvalidacionComisionProf> find(Integer correl) {
        Criteria criteria = getSession().createCriteria(ConvalidacionComisionProf.class);

        criteria.add(eq("id.ccopCod", correl));
        criteria.createAlias("profesor", "profesor");
        criteria.addOrder(asc("profesor.profPat"));
        criteria.addOrder(asc("profesor.profMat"));

        return criteria.list();
    }
}
